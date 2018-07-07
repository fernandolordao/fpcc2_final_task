package checkers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import core.SamplingAlgorithm;
import core.algorithms.OneDisabledSampling;
import core.algorithms.RandomSampling;

public class RandomSamplingBugChecker {

public static final String SOURCE_LOCATION = "bugs/";
	
	public int configurations = 0;
	public int bugs = 0;
	
	public static List<String> allBugs = new ArrayList<>();
	public static List<String> allConfigs = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		double start = System.currentTimeMillis();
		//System.out.println("CONFIGS: 1");
		RandomSamplingBugChecker checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 1;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 2");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 2;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 3");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 3;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 4");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 4;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 5");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 5;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 6");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 6;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 7");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 7;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 8");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 8;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 9");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 9;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 10");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 10;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 11");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 11;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 12");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 12;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 13");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 13;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 14");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 14;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 15");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 15;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 16");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 16;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 17");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 17;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 18");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 18;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 19");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 19;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 20");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 20;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 21");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 21;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 22");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 22;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 23");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 23;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 24");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 24;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 25");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 25;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 26");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 26;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 27");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 27;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 28");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 28;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 29");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 29;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 30");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 30;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 31");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 31;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 32");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 32;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 33");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 33;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 34");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 34;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 35");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 35;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 36");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 36;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 37");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 37;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 38");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 38;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 39");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 39;
		checker.checkhSampling(new RandomSampling());
		
		//System.out.println("CONFIGS: 40");
		checker = new RandomSamplingBugChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		RandomSampling.NUMBER_CONFIGS = 40;
		checker.checkhSampling(new RandomSampling());
		double end = System.currentTimeMillis();
		
		System.out.println("TIME: " + (end-start));
		System.out.println("AVERAGE: " + (((end-start)/40)/47));
		
		System.out.println("BUGS");
		for (String b : allBugs){
			System.out.println(b);
		}
		System.out.println("CONFIGS");
		for (String b : allConfigs){
			System.out.println(b);
		}
	}
	

	public void checkhSampling(SamplingAlgorithm algorithm) throws Exception{
		System.out.println(RandomSampling.NUMBER_CONFIGS);
		
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
		
		//System.out.println("Bugs: " + bugs);
		//System.out.println("Configurations:" + configurations + "\n");
		
		allBugs.add(bugs + "");
		allConfigs.add(configurations + "");
		
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
	
	public void checkingMissingMacros(File file, String[] macros) throws Exception{
		
		SamplingAlgorithm pairwise = new OneDisabledSampling();
		pairwise.getSamples(file);
		List<String> directives = pairwise.getDirectives(file);
		
		for (String macro : macros){
			macro = macro.replace("!", "").replace("(", "").replace(")", "");
			if (!directives.contains(macro)){
				//System.out.println("PROBLEM: " + file.getAbsolutePath());
				//System.out.println("PROBLEM: " + directives);
				//System.out.println("PROBLEM: " + macro);
			}
		}

	}
	
	
}
