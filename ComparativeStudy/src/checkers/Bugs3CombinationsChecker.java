package checkers;

import java.io.File;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import core.SamplingAlgorithm;
import core.algorithms.AllEnabledDisabledSampling;
import core.algorithms.OneDisabledSampling;
import core.algorithms.OneEnabledSampling;
import core.algorithms.StmtCoverageSampling;
import core.algorithms.TwiseSampling;

public class Bugs3CombinationsChecker {

public static final String SOURCE_LOCATION = "bugs/";
	
	public int configurations = 0;
	
	public static void main(String[] args) throws Exception {
		Bugs3CombinationsChecker checker = new Bugs3CombinationsChecker();

		checker.configurations = 0;
		System.out.println("One enabled, One Disabled and All enabled disabled");
		checker.checkhSampling(new OneEnabledSampling(), new OneDisabledSampling(), new AllEnabledDisabledSampling());
		
		checker.configurations = 0;
		System.out.println("One enabled, One Disabled and pair-wise");
		checker.checkhSampling(new OneEnabledSampling(), new OneDisabledSampling(), new TwiseSampling(2));
		
		checker.configurations = 0;
		System.out.println("One enabled, One Disabled and three-wise");
		checker.checkhSampling(new OneEnabledSampling(), new OneDisabledSampling(), new TwiseSampling(3));
		
		checker.configurations = 0;
		System.out.println("One enabled, One Disabled and four-wise");
		checker.checkhSampling(new OneEnabledSampling(), new OneDisabledSampling(), new TwiseSampling(4));
		
		checker.configurations = 0;
		System.out.println("One enabled, One Disabled and stmt-coverage");
		checker.checkhSampling(new OneEnabledSampling(), new OneDisabledSampling(), new StmtCoverageSampling());
		
		checker.configurations = 0;
		System.out.println("One enabled, All enabled disabled and pair-wise");
		checker.checkhSampling(new OneEnabledSampling(), new AllEnabledDisabledSampling(), new TwiseSampling(2));
		
		checker.configurations = 0;
		System.out.println("One enabled, All enabled disabled and three-wise");
		checker.checkhSampling(new OneEnabledSampling(), new AllEnabledDisabledSampling(), new TwiseSampling(3));
		
		checker.configurations = 0;
		System.out.println("One enabled, All enabled disabled and four-wise");
		checker.checkhSampling(new OneEnabledSampling(), new AllEnabledDisabledSampling(), new TwiseSampling(4));
		
		checker.configurations = 0;
		System.out.println("One enabled, All enabled disabled and stmt-coverage");
		checker.checkhSampling(new OneEnabledSampling(), new AllEnabledDisabledSampling(), new StmtCoverageSampling());
	
		checker.configurations = 0;
		System.out.println("One enabled, pair-wise and stmt-coverage");
		checker.checkhSampling(new OneEnabledSampling(), new TwiseSampling(2), new StmtCoverageSampling());
		
		checker.configurations = 0;
		System.out.println("One enabled, three-wise and stmt-coverage");
		checker.checkhSampling(new OneEnabledSampling(), new TwiseSampling(3), new StmtCoverageSampling());
		
		checker.configurations = 0;
		System.out.println("One enabled, four-wise and stmt-coverage");
		checker.checkhSampling(new OneEnabledSampling(), new TwiseSampling(4), new StmtCoverageSampling());
		
		checker.configurations = 0;
		System.out.println("One disabled, All enabled disabled and pair-wise");
		checker.checkhSampling(new OneDisabledSampling(), new AllEnabledDisabledSampling(), new TwiseSampling(2));
		
		checker.configurations = 0;
		System.out.println("One disabled, All enabled disabled and three-wise");
		checker.checkhSampling(new OneDisabledSampling(), new AllEnabledDisabledSampling(), new TwiseSampling(3));
		
		checker.configurations = 0;
		System.out.println("One disabled, All enabled disabled and four-wise");
		checker.checkhSampling(new OneDisabledSampling(), new AllEnabledDisabledSampling(), new TwiseSampling(4));
		
		checker.configurations = 0;
		System.out.println("One disabled, All enabled disabled and stmt-coverage");
		checker.checkhSampling(new OneDisabledSampling(), new AllEnabledDisabledSampling(), new StmtCoverageSampling());
		
		checker.configurations = 0;
		System.out.println("All enabled disabled, pair-wise and stmt-coverage");
		checker.checkhSampling(new AllEnabledDisabledSampling(), new TwiseSampling(2), new StmtCoverageSampling());
		
	}
	

	public void checkhSampling(SamplingAlgorithm algorithm1, SamplingAlgorithm algorithm2, SamplingAlgorithm algorithm3) throws Exception{
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
				List<List<String>> sampling3 = algorithm3.getSamples(srcFile);
				
				this.configurations += (sampling1.size() + sampling2.size() + sampling3.size());
				
				detected = this.doesSamplingWork(macros, sampling1, sampling2, sampling3);
				if (detected){
					bugsDetected++;
					break;
				} 
				
			}

			
		}
		
		System.out.println("Bugs: " + bugsDetected);
		System.out.println("Configs: " + configurations + "\n");
	}
	
	public boolean doesSamplingWork(String[] macros, List<List<String>> sampling1, List<List<String>> sampling2, List<List<String>> sampling3) throws Exception{
		for (List<String> s : sampling2){
			sampling1.add(s);
		}
		
		for (List<String> s : sampling3){
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
