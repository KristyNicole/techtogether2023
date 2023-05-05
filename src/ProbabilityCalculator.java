import java.util.ArrayList;
import java.util.Scanner;

/*
 * TechTogether Miami 2023 Hackathon
 * 5/5/2023
 * Ishel Zain, Thi Pham, Kristy Hamlin
 * 
 * This class will use the JobReader class to calculate the match score
 * between a job posting and a job applicant, given a description and 
 * years of experience. This match score represents the probability of hire.
 * 
 *  
 */
public class ProbabilityCalculator {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Job Posting Information~~~~~~~~~~");
		System.out.println("Years of Experience desired: ");
		int jobYears = in.nextInt();
		System.out.println("Name of Job Post .txt file: ");
		String jobTextFile = in.next();
				
		JobReader job = new JobReader(jobYears, jobTextFile);
		
		System.out.println("Job Applicant Information~~~~~~~~~~");
		System.out.println("Years of Experience of Applicant: ");
		int appYears = in.nextInt();
		System.out.println("Name of resume .txt file: ");
		String resumeFile = in.next();
		
		
		JobReader applicant = new JobReader(appYears, resumeFile);
		
		double probability = CalculateProbability(job, applicant);
		
		System.out.println("The probability of getting hired is: " + probability*100 + "%");
		
		in.close();

	}
	public static double CalculateProbability(JobReader jobPost, JobReader jobApplicant) {
		//first, calculate a match score between keywords in the job post and the job application.
		//The match score will be the number of matching keywords divided by the total keywords
		//in the job posting. 
		
		double probability;
		
		ArrayList<String> jobKeywords = jobPost.getKeywords();
		ArrayList<String> applicantKeywords = jobApplicant.getKeywords();
		
		int denominator = jobKeywords.size();
		int numerator = 0;
		
		for(int i = 0; i < jobKeywords.size(); i++) {
			
			if(applicantKeywords.contains(jobKeywords.get(i))) {
				numerator++;
			}
			
		}
		
		double keywordScore = (double)numerator/(double)denominator;
		
		//second, calculate a match score between the years the applicant has
		//and the years the post requires. Being over-qualified or under-qualified
		//can both be concerns, with larger discrepancies meaning more of a mismatch.
		//Therefore we will take the difference and square it. 
		int desiredYears = jobPost.getYears();
		int applicantYears = jobApplicant.getYears();
		double d = (double)desiredYears - (double)applicantYears;
		double experienceScore = 0;
		
		if(Math.abs(d) < 4.0) {
			
			experienceScore = (1.0 - (Math.pow(d, 2.0)/16));
			
		}
		
		//The probability is a 50% weighted score between the keyword match score
		//and the experience score.
		
		probability = (0.5 * experienceScore) + (0.5 * keywordScore);
		
		//For testing:
		System.out.println("Keyword Match Score: " + keywordScore);
		System.out.println("Experience Score: " + experienceScore);
		//System.out.println("Estimated Probability: " + probability);
		
		return probability;
	}

}
