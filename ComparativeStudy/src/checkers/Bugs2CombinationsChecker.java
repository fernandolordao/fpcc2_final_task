package checkers;

import java.io.File;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import core.SamplingAlgorithm;
import core.algorithms.AllEnabledDisabledSampling;
import core.algorithms.OneDisabledSampling;
import core.algorithms.OneEnabledSampling;
import core.algorithms.RandomSampling;
import core.algorithms.StmtCoverageSampling;
import core.algorithms.TwiseSampling;

public class Bugs2CombinationsChecker {

	public static final String SOURCE_LOCATION = "bugs/";
	
	public int configurations = 0;
	
	public static void main(String[] args) throws Exception {
		Bugs2CombinationsChecker checker = new Bugs2CombinationsChecker();

		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Pairwise and One Enabled:");
		checker.checkhSampling(new TwiseSampling(2), new OneEnabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Pairwise and One Disabled:");
		checker.checkhSampling(new TwiseSampling(2), new OneDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Pairwise and All Enabled Disabled:");
		checker.checkhSampling(new TwiseSampling(2), new AllEnabledDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Threewise and One Enabled:");
		checker.checkhSampling(new TwiseSampling(3), new OneEnabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Fourwise and One Enabled:");
		checker.checkhSampling(new TwiseSampling(4), new OneEnabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Threewise and One Disabled:");
		checker.checkhSampling(new TwiseSampling(3), new OneDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Fourwise and One Disabled:");
		checker.checkhSampling(new TwiseSampling(4), new OneDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Threewise and All Enabled Disabled:");
		checker.checkhSampling(new TwiseSampling(3), new AllEnabledDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Fourwise and All Enabled Disabled:");
		checker.checkhSampling(new TwiseSampling(4), new AllEnabledDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("One Enabled and All Enabled Disabled:");
		checker.checkhSampling(new OneEnabledSampling(), new AllEnabledDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("One Enabled and One Disabled:");
		checker.checkhSampling(new OneEnabledSampling(), new OneDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("One Disabled and All Enabled Disabled:");
		checker.checkhSampling(new OneDisabledSampling(), new AllEnabledDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Stmt coverage and One Enabled:");
		checker.checkhSampling(new StmtCoverageSampling(), new OneDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Stmt coverage and One disabled:");
		checker.checkhSampling(new StmtCoverageSampling(), new OneDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Stmt coverage and All enabled disabled:");
		checker.checkhSampling(new StmtCoverageSampling(), new AllEnabledDisabledSampling());
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Stmt coverage and Pairwise:");
		checker.checkhSampling(new StmtCoverageSampling(), new TwiseSampling(2));
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Stmt coverage and Threewise:");
		checker.checkhSampling(new StmtCoverageSampling(), new TwiseSampling(3));
		
		checker.configurations = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		System.out.println("Stmt coverage and Fourwise:");
		checker.checkhSampling(new StmtCoverageSampling(), new TwiseSampling(4));
		
	}

	public void checkhSampling(SamplingAlgorithm algorithm1, SamplingAlgorithm algorithm2) throws Exception{
		File inputWorkbook = new File("bugs.xls");
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(0);
		
		int bugsDetected = 0;
		
		
		String project, path, presenceCondition = null;
		
		for (int i = 0; i < sheet.getRows(); i++) {
			
			project = sheet.getCell(0, i).getContents();
			path = sheet.getCell(3, i).getContents();
			presenceCondition = sheet.getCell(4, i).getContents();
			
			presenceCondition = presenceCondition.replaceAll("\\s", "");
			String[] options = presenceCondition.split("\\)\\|\\|\\(");
			boolean detected = false;
			
			for (String option : options){
				String[] macros = option.split("&&");
				
				File srcFile = new File(BugsChecker.SOURCE_LOCATION + project + "/" + path);
				
				List<List<String>> sampling1 = algorithm1.getSamples(srcFile);
				List<List<String>> sampling2 = algorithm2.getSamples(srcFile);
				
				this.configurations += (sampling1.size() + sampling2.size());
				
				
				detected = this.doesSamplingWork(macros, sampling1, sampling2);
				if (detected){
					bugsDetected++;
					break;
				} 
				
			}

			
		}
		
		System.out.println("Bugs: " + bugsDetected);
		System.out.println("Configs: " + configurations + "\n");
	}
	
	public boolean doesSamplingWork(String[] macros, List<List<String>> sampling1, List<List<String>> sampling2) throws Exception{
		for (List<String> s : sampling2){
			sampling1.add(s);
		}
		
		for (List<String> configuration : sampling1){
			boolean containsAll = true;
			for (String macro : macros){
				macro = macro.replace("(", "").replace(")", "").replaceAll("\\s", "");
				if (!configuration.contains(macro)){
					containsAll = false;
				}
			}
			if (containsAll){
				return true;
			}
		}
		return false;
	}
	
}
