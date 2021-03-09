# availity_assessment
Repository for housing submissions for Availity's code assessment.

Submission key:

Tell me about your proudest professional achievement. It can also be a personal or
school project.


While I was at Wells Fargo, my team encountered a production outage caused by an overload of large messages being sent rapidly. 
We spent most of that day and almost all of that night triaging the issue and building an emergency patch. 
This temporary fix prevented further outages until we could build and test a full release to include the fix as well as other safety measures.

The problem began when one of our message producing customers gradually ramped the size of their XML messages to around 25MB.
Against our design and without our involvement, they would send us one copy of each of these messages about every ten seconds to each of their handful of downstream environments.
This exposed a design flaw in our system around persisting incomplete message processing. 

Our workflow was designed to persist incomplete messages at various points where there was a likelihood of failure.
The persisted message would then be rolled back for another thread or processing node to pick up the incomplete message to try again.
The design flaw was that if the persistence operation went on long enough, the remaining threads of that processing node would also be halted, causing a bottleneck where high-priority messages couldn't get through.
Some of these high-priority messages getting delayed resulted in regulatory fines being assessed to the firm, which generated tremendous attention.
A further flaw was discovered that our workflow included unnecessary persistence steps, nearly doubling the amount of dead time per workflow instance.

I was tasked with researching this and trying to manage the message flow while my manager was pulled into root cause analysis calls and received berating from higher managers and traders from the floor.
I discovered the unnecessary persistence steps and also captured timing data that showed how the blockage would occur across the entire processing node.

My manager and I worked into the night building a temporary solution and planning the future permanent one. 
We tightened our workflow, put in a safety mechanism that would allow other threads to continue processing, and built in some better analytics reporting to catch potential issues like this faster. 
I learned a great deal about managing high-profile outages, including brevity of communication by audience, not getting distracted by shifting blame particularly when your system has problems to fix, and understanding that severe issues like this require tremendous effort to work through when they're allowed to happen. 
I consider it a great personal achievement that we were able to complete our work in such a quick timeframe and that I was able to rise to the occasion to be there to get the job done.

==============================================

Tell me about something you have read recently that you would recommend and why.
(Can be a Github Repo, Article, Blog, Book, etc)

A hyper-recent post I read looked into utilizing a new API introduced in Java 16 to work a familiar problem. 
The API is the new Vector API (jdk.incubator.vector) and the author looked at implementing a SIMD (single instruction, multiple data) solution for FizzBuzz.
I'll admit that a good bit of the discourse is well above me, but I found it enlightening and humorous to follow the author's process.
Enlightening because he used a well-understood problem to demonstrate a high-level concept and provide an easy-to-follow example for a high-performance operation.
Humorous because it feels like a tremendously over-engineered solution to a simple problem.

https://www.morling.dev/blog/fizzbuzz-simd-style/

What I can understand about this is that it's a novel way to parallelize what's traditionally (at least for Java solutions I've seen) handled sequentially.
Since the Vector API works with numerical generics (e.g. Integer, Short), he substitutes -1, -2, and -3 for FIZZ, BUZZ, and FIZZBUZZ.
The Vector API also doesn't have an operation like modulo, so he leverages a masking strategy.
He sets up just enough masks to cover the possible 3/5/3&5 combinations for the range of integers he runs against.
He then demonstrates multiple ways to run through this operation and reports the benchmarks of his system. 
He finds that the Vector API is able to distribute his calculations to finish them 4-8x faster than running through them sequentially.

The benefit of this experiment shows a few interesting tricks to squeeze performance out of a simple problem, what kind of use case a future API fits, and how one might expect it to behave given a known domain.

==============================================

How would you explain what Availity does to your grandmother?

TODO

==============================================

Coding exercise: You are tasked to write a checker that validates the parentheses of a
LISP code. Write a program (in Java or JavaScript) which takes in a string as an input and
returns true if all the parentheses in the string are properly closed and nested.

src/main/java/com/chasegn/ParenthesesValidator

==============================================

Coding exercise: For frontend engineer: Healthcare providers request to be part of
the Availity system. Using HTML, CSS, and Javascript create a registration user
interface so healthcare providers can electronically join Availity. Use Javascript
for basic data validation. The following data points should be collected:
- First Name
- Last Name
- NPI number
- Business Address
- Telephone Number
- Email address

availity-registration/src

cd availity-registration
npm run build
npm start

==============================================

Coding exercise: Availity receives enrollment files from various benefits management
and enrollment solutions (I.e. HR platforms, payroll platforms).  Most of these files are
typically in EDI format.  However, there are some files in CSV format.  For the files in CSV
format, write a program that will read the content of the file and separate enrollees by
insurance company in its own file. Additionally, sort the contents of each file by last and
first name (ascending).  Lastly, if there are duplicate User Ids for the same Insurance
Company, then only the record with the highest version should be included. The
following data points are included in the file:
- User Id (string)
- First Name (string)
- Last Name (string)
- Version (integer)
- Insurance Company (string)

src/main/java/com/chasegn/CSVHarvester

src/main/java/com/chasegn/EnrollmentRecord

==============================================
