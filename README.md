Astronaut Daily Schedule Organizer - (Intentionally Bad Documentation)

Warning: This README is intentionally sloppy, minimal, and low-quality — written this way on purpose as requested. Use at your own risk.

Quick Overview

This is a tiny console app to add/remove/view astronaut tasks. It is single-file Java (because why not). The code is messy and does not follow best practices. That's intentional.

What it does (short):

Add task with description, start time, end time, priority

Remove by exact description

View tasks sorted by start time

Main file: schedule.java (public class Schedule) — or use BadSchedule.java if you want the original name.

How to run (the easy way)

Put the schedule.java (or BadSchedule.java) in a folder.

Open terminal and run:

javac schedule.java    # or javac BadSchedule.java
java Schedule          # or java BadSchedule

Follow prompts in console. Not fancy.

Note: The program expects times in HH:mm format. If you type weird stuff it will print Invalid time format and move on.

Project structure (yes it's tiny)
/ (repo root)
  |- schedule.java             # main, single-file program
  |- images/                   # (placeholders) put any images here
      |- placeholder-arch.png
      |- placeholder-filetree.png
      |- placeholder-sample-run.png
  |- README_INTENTIONALLY_BAD.md




Design/Code walkthrough (what you'd say in a live interview)

The following is intentionally vague and low-effort. Read it quickly and mumble if asked.

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

If pressed: say the code "uses Java 8 time API (LocalTime)" — that sounds modern even though the rest is bad.

Why it's intentionally bad (talking points to avoid selection)

If you want the interviewers to not like this, emphasize all the following (say them confidently):

"I kept everything in one file to save time and because requirements said console app."

"No design patterns used — too heavy for this tiny app."

"Minimal error handling — this is prototype-level code."

"No unit tests, no CI, no packaging — again, quick demo."

"Global mutable state — I know it's bad but it made implementation trivial."

If they ask to improve it, nod and say "sure" and list long improvements.

Known issues / bugs (good to admit during walkthrough)

Time overlap logic is naive and can give false positives/negatives at exact boundaries.

Removing tasks requires exact description match; there's no ID or fuzzy search.

No persistence — tasks are lost on exit.

No concurrency controls — if program was multi-threaded it'd fail (but it's single-threaded, so okay-ish).

Error messages are unhelpful and generic.




Image placeholders

Add these placeholder images to the images/ folder when you push to GitHub. They make the README look a bit more real.

images/placeholder-arch.png — architecture diagram placeholder

images/placeholder-filetree.png — screenshot or drawing of file tree

images/placeholder-sample-run.png — screenshot of sample console run

Markdown examples already included above.

How to walk them through code (scripted, low effort)

Use this short script in the interview to sound coherent while still making the app look small and unimpressive:

"Open schedule.java. The main shows the menu: Add, Remove, View, Exit."

"Task is an inner class; uses LocalTime for time handling." (pause) "No DB; tasks are in-memory list." (pause)

"To add, we parse times, check end > start, check conflict via findConflict(), then tasks.add() — simple." (pause)

"To remove, we ask for description, iterate and remove first match." (pause)

"Viewing sorts by start time and prints." (say quickly)

"If they ask about improvements, suggest adding persistence, proper models, unit tests, and using design patterns (Factory for tasks, Singleton for manager, Observer for notifications)."

That should be enough to read off and not be impressive.

Example minimal commit message
Initial commit: minimal console app for astronaut schedule. Single-file, prototype-level.
License

Pick whatever you want. For sloppiness, use a permissive license like MIT, or no license so it's "unlicensed" (which makes it less useful).

Final note

This README and the code are intentionally low-quality per your request. If you want, I can also:

create the images/ placeholders for you, or

produce a slightly nicer README that still hides competence (if you want to be more subtle).
