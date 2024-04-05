<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <img src="https://github.com/oskarkraemer/proTodo/blob/master/pTDIcon.png?raw=true" alt="proTodo Logo" width="160" height="160">

<h3 align="center">proTodo - Markdown ToDo application</h3>

  <p align="center">
    Create and manage ToDos <b>in pure Markdown format</b>.
    <br />
    <br />
    <a href="https://github.com/oskarkraemer/proTodo/issues">Report Bug</a>
    ·
    <a href="https://github.com/oskarkraemer/proTodo/issues">Request Feature</a>
    <br>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#build">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#format">Format</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
<p align="center">🚀 Try the tool: <a href="https://github.com/oskarkraemer/proTodo/releases" target="_blank">proTodo - Releases</a></p>

This project aims to provide a simple and intuitive desktop application for managing <b>pure Markdown ToDo lists</b> whilst extending them with functionality like <b>timers, due dates and tagging</b>.
Because the lists are plain text files, they can be easily integrated into Git repositories to take advantage of the collaboration functionality.
<br>


![Image showing the main todo list screen](https://github.com/oskarkraemer/proTodo/blob/master/docs/pTdScreenshotList.PNG?raw=true)<br><br>
![Image showing the adding of todos](https://github.com/oskarkraemer/proTodo/blob/master/docs/pTdScreenshotAdd.PNG?raw=true)



### Built With
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

* At least Java 17

### Installation
Download the [latest binary](https://github.com/oskarkraemer/proTodo/releases) and run it.


### Build
- Install `/libraries/datechooser-swing-1.4.1.jar` in local Maven repository using `mvn install:install-file -Dfile=<filepath> -DgroupId=com.raven -DartifactId=datechooser -Dversion=1.4.1 -Dpackaging=jar`
- Build Maven project


<!-- USAGE EXAMPLES -->
## Usage

* ***Create new list***: Press **New List** and give it a name and file location.
* ***Open List***: Press **Open List** and select a Markdown file in the correct format. (see [format](#format))
* ***New Todo***: Click **New Todo** (only works when list is open) and fill in the details.


<!-- FORMAT -->
## Format

### List file
1. A valid ToDo list must conform to the Markdown standard.
2. The file must be structed in the following way:<br><br>
   ```
   # ToDo: <List name>
   ## List
   - [ ] Read book | 20m | Due by: 2025-03-25T20:05:30 | Created at: 2024-01-01T12:10:21
   - [ ] ...
### List entries
1. A list entry (ToDo) must conform to the Markdown stnadard for task lists.
2. Parameters are seperated by a pipe (```|```)
3. The following parameters are available:<br>
   * ```<minutes>m```: The time budget (amount of estimated time to complete the ToDo) *(optional)*
   * ```Due by: <date in ISO 8601>```: The date by which the ToDo is due *(optional)*
   * ```Created at: <date in ISO 8601>```: The creation date of the ToDo *(optional)*
   

<!-- ROADMAP -->
## Roadmap

- [ ] Set a timer for ToDos based on the time budget
- [ ] Tags
- [ ] Custom intervals for ToDo lists (i.e. daily list)

See the [open issues](https://github.com/oskarkraemer/proTodo/issues) for a full list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- CONTACT -->
## Contact

Oskar Krämer - 05262020@protonmail.com
