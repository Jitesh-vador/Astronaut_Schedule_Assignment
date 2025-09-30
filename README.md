#Astronaut Daily Schedule Organizer - (Intentionally Bad Documentation)



#Quick Overview

This is a tiny console app to add/remove/view astronaut tasks

#What it does :

1. Add task with description, start time, end time, priority

2. Remove by exact description

3. View tasks sorted by start time

Main file: schedule.java (public class Schedule) — or use BadSchedule.java if you want the original name.

#How to run 

1. Put the schedule.java in a folder.

2. Open terminal and run:

javac schedule.java    
java Schedule         


Project structure 
/ (repo root)
  |- schedule.java             
  |- Readme.md




#Design/Code walkthrough 

Entry point: public static void main(String[] args) in Schedule.

Starts a Scanner and prints a dumb menu.

Data model: inner static class Task with fields desc, start, end, priority.

No getters/setters, because it's simpler this way.

Data storage: static List<Task> tasks = new ArrayList<>();

Global mutable list (easy to explain: "keeps state in memory, no DB needed").

Add flow: addFlow(Scanner sc) reads inputs, parses LocalTime, checks end > start, checks naive conflict, and adds.

Conflict detection uses a simple overlap check that is kinda wrong at edges.

Remove flow: removeFlow(Scanner sc) removes first Task with exact matching description.

Removes only first match (point out as limitation if asked).

View flow: viewFlow() sorts tasks by start time and prints them.



#Known issues / bugs 

Time overlap logic is naive and can give false positives/negatives at exact boundaries.

Removing tasks requires exact description match; there's no ID or fuzzy search.

No persistence — tasks are lost on exit.

No concurrency controls — if program was multi-threaded it'd fail (but it's single-threaded, so okay-ish).

Error messages are unhelpful and generic.





  <img width="442" height="68" alt="image" src="https://github.com/user-attachments/assets/6c2d55cd-1ac9-43f9-adfa-e5b8ca602db8" />
<img width="566" height="209" alt="image" src="https://github.com/user-attachments/assets/5b89cffe-f3fc-4cd3-9be6-1483756c8e7c" />
<img width="413" height="110" alt="image" src="https://github.com/user-attachments/assets/6d9a74c6-7bea-4d59-b36c-f03f921f6c5a" />



