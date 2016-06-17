# argus-cit-intellij: Argus Code Inspection Tool for Intellij


## Developing argus-cit-intellij

In order to take part in argus-cit-intellij development, you need to:

1. Install the following software:
    - IntelliJ IDEA 14 or higher with compatible version of Scala plugin

2. Fork this repository and clone it to your computer

  ```
  $ git clone https://github.com/arguslab/argus-cit-intellij.git
  ```

3. Open IntelliJ IDEA, select `File -> New -> Project from existing sources`
(if from initial window: `Import Project`), point to
the directory where Scala plugin repository is and then import it as SBT project.

4. When importing is finished, go to argus-cit-intellij repo directory and run

  ```
  $ git checkout .idea
  ```

  in order to get artifacts and run configurations for IDEA project.
  
5. If you want to build argus-cit-intellij from command line, go to argus-cit-intellij repo directory and run

   ```
   $ tools/bin/sbt clean compile test
   ```

## How to contribute

To contribute to the argus-cit-intellij, please send us a [pull request](https://help.github.com/articles/using-pull-requests/#fork--pull) from your fork of this repository!

For more information on building and developing argus-cit-intellij, please also check out our [guidelines for contributing](CONTRIBUTING.md). People who provided excellent ideas are listed in [contributor](CONTRIBUTOR.md).
