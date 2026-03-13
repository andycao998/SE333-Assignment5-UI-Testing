[![Playwright Workflow](https://github.com/andycao998/SE333-Assignment5-UI-Testing/actions/workflows/playwright.yml/badge.svg)](https://github.com/andycao998/SE333-Assignment5-UI-Testing/actions/workflows/playwright.yml)

<h2>Overview</h2>
This repository is for part 2 of Assignment 5 (User-Interface Testing). This includes two separate packages for manual/traditional testing and LLM-automated testing of UI using Playwright.

- [Traditional](src/test/java/org/example/playwrightTraditional/TestTraditional.java)
- [LLM](src/test/java/org/example/playwrightLLM/TestLLM.java)

<h2>Continuous Integration</h2>
CI workflow through GitHub Actions that installs playwright and browsers before running tests when pushing to main.
<br>
<br>

Workflow found [here](.github/workflows/playwright.yml)

<h4>Videos</h4>

-[Traditional](videos/playwrightTraditional.webm)
-[LLM](videos/playwrightLLM.webm)

<h2>Reflection</h2>
I found manual UI testing quite cumbersome especially adjusting to using the tool. I had to run several trials to click around and cross compare with the output/tests it was generating to see how it worked. One aspect of this was figuring out what elements I wanted to click on to best display the information that was being asked of in the prompt because some elements were enclosed by others and some didn't contain the text content that I expected. Navigating the site was simple, but I had to write the assertions manually, which was the hardest part. I saw that there were buttons to assert visually, or through text content, but I wasn't able to get them working. It was a lot of trial and error having to save an assertion and wait > 10 seconds to see the browser navigate and see where it encountered errors, but I'm overall impresed with the accuracy and visual display of test steps.
<br>
Surprisingly, I found AI-assisted UI testing much easier and faster compared to the manual testing I did. Part of it was familiarity and knowing what to look for because I already did it myself, but the agent was able to successfully pass testing with fewer iterations than what I had to do for manual testing. It seemingly had a lot easier time navigating the site through clicking on the relevant elements even though I found myself tripped up at certain points. I broke the prompt down into many sections in anticipation that it would get confused by a long list of steps, which might've helped. I also found it interesting that it figured out to start editing in the LLM package and TestLLM.java by itself without me prompting it to. That does suggest it potentially had access to my TestTraditional.java tests first, but I specified not to read, use, or edit anything other than TestLLM.java. One thing it did poorly, however, was that it always mentioned that it succeeded even when it didn't. For example after generating all of the tests, it claimed that they were all passing when they were not because I ran mvn test and received errors. I had a back and forth telling it that there were issues, but it checked again and responded that it didn't encounter any without making any changes. Eventually, I had to provide the build failure output for it to adjust. Full disclosure that I had to activate a free trial because I ran out of messages - the model swapped from Grok Code Fast 1 to GPT-5.3-Codex, which may or may not have influenced the ease of use.
<br>
Overall, even with the AI-assisted ease of use, I would probably stick to manual testing because the process felt more reliable when I could see what was being clicked on and fix my tests more cleanly. The use case this time was quite simple, but I imagine it would be more difficult for the AI on more complicated workflows. One limitation I encountered was consistency when running the tests because I was able to get the build to pass and successfully see the video results, but when I pushed to the workflow, there was suddenly failures when running the tests. The tests seem pretty flaky because of how I had to specify specific n-th elements of some identifier, which caused all sorts of issues. In general, UI testing like this seems really difficult to maintain. Since everything is going based off of elements, any updates to sites, especially large updates, will require a lot of time to sort through these test cases. Perhaps an AI agent could be useful to quickly confirm the accuracy and reliability of existing tests whilst manual work could be done after to review and confirm changes.

