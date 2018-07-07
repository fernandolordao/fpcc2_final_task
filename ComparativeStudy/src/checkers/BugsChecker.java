package checkers;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import core.SamplingAlgorithm;
import core.algorithms.AllEnabledDisabledSampling;
import core.algorithms.OneDisabledSampling;
import core.algorithms.OneEnabledSampling;
import core.algorithms.TwiseSampling;

public class BugsChecker {

	public static final String SOURCE_LOCATION = "bugs/";
	
	public int configurations = 0;
	public int bugs = 0;
	
	private static String[][] stats;

	public static void main(String[] args) throws Exception {
		File inputWorkbook = new File("bugs.xls");
		Workbook w = Workbook.getWorkbook(inputWorkbook);
		Sheet sheet = w.getSheet(0);

		stats = new String[7][sheet.getRows()];
		System.out.println("Total cataloged of bugs: " + sheet.getRows());

		for (int i = 0; i < sheet.getRows(); i++) {
			stats[0][i] = sheet.getCell(0, i).getContents(); //Project
			stats[1][i] = sheet.getCell(3, i).getContents(); //Path
			stats[2][i] = sheet.getCell(4, i).getContents(); //PresenceCondition
		}
		
		double start = System.currentTimeMillis();
		BugsChecker checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("One-Enabled Sampling");
		checker.checkhSampling(new OneEnabledSampling());
		double end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		
		start = System.currentTimeMillis();
		checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("One-Disabled Sampling");
		checker.checkhSampling(new OneDisabledSampling());
		end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		
		start = System.currentTimeMillis();
		checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("All-Enabled-Disabled Sampling");
		checker.checkhSampling(new AllEnabledDisabledSampling());
		end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		
		start = System.currentTimeMillis();
		checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("Pair-wise Sampling");
		checker.checkhSampling(new TwiseSampling(2));
		end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		
		/*
		start = System.currentTimeMillis();
		checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("Three-wise Sampling");
		checker.checkhSampling(new TwiseSampling(3));
		end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");

		start = System.currentTimeMillis();
		checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("Four-wise Sampling");
		checker.checkhSampling(new TwiseSampling(4));
		end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		
		start = System.currentTimeMillis();
		checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("Five-wise Sampling");
		checker.checkhSampling(new TwiseSampling(5));
		end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		
		start = System.currentTimeMillis();
		checker = new BugsChecker();
		checker.configurations = 0;
		checker.bugs = 0;
		System.out.println("Six-wise Sampling");
		checker.checkhSampling(new TwiseSampling(6));
		end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "\n");
		*/
		
		FileWriter fw = new FileWriter("stats.csv");
		
		fw.write("project,path,presence_condition,alg_one_enabled,alg_one_disabled,alg_all_enabled_disabled,alg_pair_wise");
		
		for (int i = 0; i < sheet.getRows(); i++) {
			fw.write("\n" + stats[0][i] + "," + stats[1][i] + "," + stats[2][i] + "," + stats[3][i] + "," + stats[4][i] + "," + stats[5][i] + "," + stats[6][i]);
		}
		fw.close();
		
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
			
			if (OneEnabledSampling.class.isInstance(algorithm))
				stats[3][i] = detected?"1":"0";
			else if (OneDisabledSampling.class.isInstance(algorithm))
				stats[4][i] = detected?"1":"0";
			else if (AllEnabledDisabledSampling.class.isInstance(algorithm))
				stats[5][i] = detected?"1":"0";
			else if (TwiseSampling.class.isInstance(algorithm))
				stats[6][i] = detected?"1":"0";
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
