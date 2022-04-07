---
layout: page
title: Jet Tan Ee Kiat's Project Portfolio Page
---

### Project: Teaching Assistant’s Personal Assistant (TAPA)

TAPA is a desktop contact management application used to manage students' contact and progress. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=medajet&breakdown=true)

* **New Feature**:
  * Added the ability to `find` students by specifying name and matriculation number. [#47](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/47)
    * What it does: Allows the user to search for students in TAPA based on their name or matriculation number.
    * Justification: This feature allows users to easily find students of interest, allowing TAs to easily get their contact details or for follow-up commands.
    * Highlights: The original `find` feature allows users to find students by keywords in their names, but due to the use of additional fields I believe the functionality was insufficient and added an additional ability to search by matriculation number. For this purpose, I created an additional predicate for the purpose of matching matriculation numbers.

* **New Feature**:
  * Added the ability to filter students by module code. [#105](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/105)
    * What it does: Allows the user to filter students in TAPA by their module code.
    * Justification: This feature allows users to easily filter students by their module codes, allowing TAs to easily get their contact details or for follow-up  commands.
    * Highlights: This feature was originally meant to be a standalone command separate from `find`, but I decided to make it an extension of it as there is some overlap in functionality. This proved tricky as it meant there were more complex cases to handle during input validation, but I was able to implement it successfully.

* **New Feature**:
  * Added the ability to delete all students in a particular module. [#135](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/135)
    * What it does: Deletes all students of a particular module by the inputted module code.
    * Justification: This feature allows users to easily remove all students of a particular module after the semester is over.

* **Enhancements to existing features**:
  * Modified the `delete` feature to allow deletion by a student's matriculation number. [#56](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/56)
    * Justification: The `delete` command ships with the default AB3, but only takes in a single index as a possible parameter. I extended it to also allow users to input the student's matriculation number as an alternative, as it will be easier for users to delete individual students if the list gets too long.
    * Highlights: I made use of the new predicate that I created for my implementation of the `find` command to implement this feature.

  * Modified the `delete` feature to allow deletion with multiple indices. [#126](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/126)
    * Justification: The `delete` command ships with the default AB3, but only takes in a single index as a possible parameter. I extended it to also allow users to input multiple indices, such that users can conveniently delete multiple students at once without having to retype the command again and again.
    
  * Wrote additional tests for new and existing features to increase code coverage. ([#113](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/113)) (TODO: More to be added)
  

* **Contribution to team-based tasks**:
  * Joint effort in renaming the product to "TAPA".
  * Joint effort in creating the product icon.
  * Joint effort in morphing product into "TAPA" by extending relevant features.
  * Maintained the issue tracker by assigning/labelling issues.
  * Tested product for cross-platform compatibility for MacOS.
  

* **Documentation**:
  * User Guide:
    * Added documentation for the features `find` and `delete` [#32](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/32)
  * Developer Guide:
    * Updated use cases [#56](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/56)
    * Added implementation details for commands: `find` [#111](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/111), `delete` and `deleteModule` [#189](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/189)


* **Community**:
  * PRs reviewed (with non-trivial review comments): [#53](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/53), [#69](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/69), [#102](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/102), [#114](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/114), [#120](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/120), [#140](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/140), [#144](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/144), [#215](https://github.com/AY2122S2-CS2103T-W09-4/tp/pull/215)
  * Reported a total of 18 bugs and suggestions for other teams in the class (as seen [here](https://github.com/medajet/ped/issues))
