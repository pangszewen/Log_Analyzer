## Log Analyzer

This project is a Java-based log analyzer developed as part of a programming assignment.
It parses cluster system logs, extracts relevant job-related events, and provides a menu-driven CLI (terminal-based frontend) for analyzing statistics.

##Features
*Job Completion Analysis
 *View summary tables by day or month
 *Count jobs with/without errors
 *Query jobs completed within a time range

*Job Scheduling Analysis
 *Summary of allocated jobs by day or month
 *View jobs scheduled in a specific time range

*Job Kill Analysis
 *Count jobs killed by specific user IDs
 *View jobs killed in a given time range
 *Generate tabular summaries

*Job Error Analysis
 *Filter by username
 *Identify error types (e.g., node errors, security violations)

*Partition Analysis
 *Jobs grouped by partition
 *Partition summary tables

## How to Run
Run the file FOP_Assignment.java in the Assignment directory.

## Log File
You can update the file path in the code.
Output files (job_completed.txt, job_sched.txt, etc.) are generated automatically when the program runs.
