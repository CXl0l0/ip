# Sigma User Guide

Wanna be more **PRODUCTIVE**? ~~Don't want to~~ **EAGER TO** do more works? Fret not, we have a solution for **YOU**!

Your personal tasks tracker, made to fit all your needs of an ideal to-do list. This application is here to help you achieve your ultimate goal of being a true **SIGMA**!!!

Introducing: Sigma
![A screenshot of Sigma's GUI](/docs/Ui.png)

## **_Sigma_** can help you to:

### Keep tracks of To-Do tasks

Simply type in the command according to the format:

```
todo [ENTER TASK NAME HERE]
```

For example:

```
todo complete CS2103T iP
```

After telling Sigma the name of the to do task, it will memorize it for you.

```
Sigma: Aight, I will remember that for you.
added: [T][ ] complete CS2103T iP
Now you have 1 task(s) in the list!
```

### Keep tracks of deadlines

Sigma doesn't just support simple to do tasks, but also tasks with _deadlines_.

```
deadline [ENTER TASK NAME HERE] /by [yyyy-mm-dd HHmm]
```

For example:

```
deadline submit iP /by 2025-02-21 2359
```

and Sigma will memorize the deadline for you too!

```
Sigma: Aight, I will remember that for you.
added: [D][ ] submit iP (By: Feb 21 2025 23:59)
Now you have 2 task(s) in the list!
```

### Keep tracks of events

Finally, **_Sigma_** also keep tracks of _events_!

```
event [ENTER TASK NAME HERE] /from [yyyy-mm-dd HHmm] /to [yyyy-mm-dd HHmm]
```

For example:

```
event recess week /from 2025-02-24 0000 /to 2025-03-03 0000
```

and Sigma will remember the event period for you!

```
Sigma: Aight, I will remember that for you.
added: [E][ ] recess week (From: Feb 24 2025 00:00 -- To: Mar 3 2025 00:00)
Now you have 3 task(s) in the list!
```

## **_Sigma_** even allows you to:

### Delete tasks

Tasks can also be deleted through the command:

```
delete [Enter Task Index]
```

For example:

```
delete 1
```

### Mark tasks as completed

**Completed** some of the tasks? Great job! Keep up the grind, but don't forget to mark down the completed tasks too!

```
mark [Enter Task Index]
```

For example:

```
mark 1
```

> [!NOTE]
> Sigma also allows you to do the opposite, unmarking tasks!

```
unmark [Enter Task Index]
```

### Edit tasks

Some _updates_ you wanted to make to the existing list? Use the edit command!

```
edit [Enter Task Index] /[Enter Target Attribute To Edit] [Enter Changes] ...
```

Attributes that are available to edit for each type of tasks:

1. To-Do Tasks
   - Task Name (with keyword '/name')
2. Deadlines
   - Task Name (with keyword '/name')
   - Deadline (with keyword '/by')
3. Events
   - Task Name (with keyword '/name')
   - Event Start Date (with keyword '/from')
   - Event End Date (with keyword '/to')

For example:

- Editing a To-Do task

```
edit 1 /name complete CS2103T tP
```

Result:

```
Successfully edited task. The task has been changed to:
[T][ ] complete CS2103T tP
```

- Editing a Deadline task

```
edit 2 /name submit CS2103T tP /by 2025-04-08 1400
```

Result:

```
Successfully edited task. The task has been changed to:
[D][ ] submit CS2103T tP (By: Apr 8 2025 14:00)
```

- Editing an Event task

```
edit 3 /name week 7 /from 2025-03-03 0000 /to 2025-03-10 0000
```

Result:

```
Successfully edited task. The task has been changed to:
[E][ ] week 7 (From: Mar 3 2025 00:00 -- To: Mar 10 2025 00:00)
```

> ![NOTE]
> You don't have to change all the attributes, can just choose to edit one of the attributes of the task. (e.g. Editing the deadline but not the task name)

### Find tasks

Too many tasks in the list? **NOT** a problem!
Simply use the 'find' command and enter the keyword of the task's name to immediately find the task!

```
find [keyword]
```

## Finally, to view your tasks

Simply enter the command:

```
list
```

and voila, your complete To-Do list is shown:

```
Sigma: Stop slacking and lock in.
1. [T][X] complete CS2103T iP
2. [D][X] submit iP (By: Feb 21 2025 23:59)
3. [E][ ] recess week (From: Feb 24 2025 00:00 -- To: Mar 3 2025 00:00)
4. [D][ ] CS2109S problem set 3 (By: Mar 15 2025 23:59)
5. [D][ ] CS2105 Assignment 1 (By: Mar 10 2025 23:59)
6. [T][ ] Revise for midterms
7. [E][ ] Club Interview (From: Feb 28 2025 15:00 -- To: Feb 28 2025 18:00)
```

Here you can also see the index of every tasks!

> [!NOTE]
> Just an arbitrarily example of what your to-do list would look like, independent from the result of examples from all aforementioned features.

## How to set up **_Sigma_** ?

Step:

1. Download the Sigma.jar file from here.
2. Create a new folder called anywhere on your computer.
3. Put the jar file into the folder.
4. Launch Sigma!
