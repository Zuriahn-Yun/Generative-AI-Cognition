# A Cognitive Framework for Modeling Coincidental Faults in N-version Programming

### Vocabulary

- **Skill Based Performance** - automatic and unconscious developed through repetition
    - Ex. Playing a sport
    - Examples in programming → Typing a text string
    - Examples of skill based error → taking a 0 for a o
- **Rule Based Performance** - Follows explicit procedures requiring conscious thought and mental resources to apply leaned rules.
    - Ex. Following written instruction to operate a machine
    - Examples of rule based errors → one tends to use a rule that has been frequently used before but not necessarily matches well with the current situation
- **N-version programming** - software development practice where multiple people will create the same thing based on the same requirements
- **Fault Tolerance** - Systems ability to continue operating without interruption or data loss, even when components fail
- **Coincident faults** - A fault that is introduced by two or more programmers in different versions of software
    - Paper definition → “A fault whose occurrences is two or more in N-version programming”
- **Fault** - “Incorrect or missing step, process or data definition in a computer program”
- **Error** - “An erroneous human behavior that leads to a software fault”
- **Knowledge-Based Performance** - When individuals face novel (new) situations and no rules are available from previous experiences
    - Errors at this level can arise from resource limitation or incomplete knowledge
- **Knowledge-Based Errors** - Human errors that occur in knowledge based performances
- **Post Completion Error** - Forgetting to attach a file when sending an email, errors like these also occur in software development
- **Forced Diversity** - Forcing the use of different programming algorithms and languages can improve faulty diversity but still result in coincident faults

### Main Ideas

- Programmers are unlikely to commit the same error in skill based performances and most likely make the same error in rule based performances.
- There is no cognitive framework for modeling the likelihood of coincident faults in n-version programming.

### Research Question

- Do the likelihoods of a fault being a coincident fault differ across various cognitive levels?
    - How do you measure cognitive levels?
        - Two professors assigned cognitive levels to each of the faults
- Do the Occurrences of coincident faults at skill based rule based knowledge based and post completion errors differ?

### Experiment

- The paper proposes a Cognitive Framework for coincident faults (ConFCF) for modeling the likelihood that different programmers will introduce the same faults.
- This was evaluated through an N-version programming experiment involving 200 programmers.

$$
POCi = OCi/P

$$

- POC = Prevalence of Occurrence of a fault - The percentage of programmers who introduced the fault (How common a fault is)
- OC = Occurrences of a fault - The number of programmers in an N-version programming study who introduced that fault.
- P = The total number of programmers who submitted code for the task
    
    ### Programming Task
    
    - Super Bubble Sort → in C language only
    - They were given a 90 minute programming exam
    - Programming was judged by an online Judge it gave several responses
        - AC → Output is the same as the standard answer  or correct
        - WA → Wrong answer
        - RF → runtime failure
        - Compilation Problem → compiler fails to compile at the source code
        - PP → the data output is correct but the format does not conform to the requirements
        - TLE → time limit exceeded
    
    ### Code Inspection
    
    - Code inspection was done on all 706 versions of programs
    - They found 70 faults, of that 31 were coincident faults
    - Every Fault was given an ID and was assigned to which task the OC gave it

### CognFCF

- Includes 4 levels of cognitive errors: skill based, rule based , knowledge based and post completion errors.

### Results

- Most faults are rule based
- The main focus was to search for coincident faults
- “The Results of this study suggest which parts of the requirements need forced diversity and which do not.
- The paper also gives recommendations for how to deal with all of the types of “performances” that are seen within N-Gram Programming.
    - There are some interesting approaches to some of the performances that yield interesting fixes.
        - For instance, For rule-based performances forced diversity is reccomended, a new strategy from this study is to assign two programmers at different expertise levels.

### What is “Forced Diversity”

What is this? 

- Combining several different approaches to solving a problem, by approaches it means more different languages or different levels of expertise.

Why is this important?

- It provides us with interesting cognitive insights on how humans approach different problems.
- If we are going to compare the faults that chat gpt might include with the faults that a human may include and combine them together, this seems like a version of forced diversity.
    - How could we combine outputs from an LLM and a person
        - What kind of faults would Chat GPT introduce
        - What kind of faults do humans introduce most often?
            - From this paper Rule Based is the most common?
        - Is there a Cognitive Level we would want to focus on preventing?
        - What cognitive level is most likely the best to approach with an LLM?