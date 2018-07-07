package checkers;

import java.io.File;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import core.SamplingAlgorithm;
import core.algorithms.OneDisabledSampling;
import core.algorithms.StmtCoverageSampling;

public class StatementCoverageBugChecker {

	public static final String SOURCE_LOCATION = "bugs/";
	
	public int configurations = 0;
	public int bugs = 0;
	
	public static void main(String[] args) throws Exception {
		double start = System.currentTimeMillis();
		StatementCoverageBugChecker checker = new StatementCoverageBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("Statement-Coverage Sampling");
		checker.checkhSampling(new StmtCoverageSampling());
		double end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		
	}

	public void checkhSampling(SamplingAlgorithm algorithm) throws Exception{
		File inputWorkbook = new File("bugs.xls");
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(0);
		
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
				
				this.checkingMissingMacros(new File(BugsChecker.SOURCE_LOCATION + project + "/" + path), macros);
				
				List<List<String>> samplings = algorithm.getSamples(new File(BugsChecker.SOURCE_LOCATION + project + "/" + path));
				this.configurations += samplings.size();
				
				detected = this.doesSamplingWork(macros, samplings);
				if (detected){
					bugs++;
					break;
				}	
			}
		}
		
		// It counts the number of configurations in C source files without faults.
		this.listAllFiles(new File("code"), algorithm);
		
		System.out.println("Bugs: " + bugs);
		System.out.println("Configurations:" + configurations);
		
		// Total number of configuration / total number of files in all projects.
		System.out.println("Configurations per file:" + ((double)configurations)/50078);
		
	}
	
	public void listAllFiles(File path, SamplingAlgorithm algorithm) throws Exception{
		if (path.isDirectory()){
			for (File file : path.listFiles()){
				this.listAllFiles(file, algorithm);
			}
		} else {
			if (path.getName().endsWith(".c")){
				List<List<String>> samplings = algorithm.getSamples(path);
				this.configurations += samplings.size();
			}
		}
	}
	
	public boolean doesSamplingWork(String[] macros, List<List<String>> samplings) throws Exception{
		for (List<String> configuration : samplings){
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
	
	public void checkingMissingMacros(File file, String[] macros) throws Exception{
		
		SamplingAlgorithm pairwise = new OneDisabledSampling();
		pairwise.getSamples(file);
		List<String> directives = pairwise.getDirectives(file);
		
		for (String macro : macros){
			macro = macro.replace("!", "").replace("(", "").replace(")", "");
			if (!directives.contains(macro)){
				System.out.println("PROBLEM: " + file.getAbsolutePath());
				System.out.println("PROBLEM: " + directives);
				System.out.println("PROBLEM: " + macro);
			}
		}

	}
	
}
