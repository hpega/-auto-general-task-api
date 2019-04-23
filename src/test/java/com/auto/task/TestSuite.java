package com.auto.task;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.auto.task.api.ToDoControllerTest;
import com.auto.task.service.TaskServiceTest;
import com.auto.task.service.TaskServiceTheoryTest;
import com.auto.task.service.ToDoServiceTest;

@RunWith(Suite.class)
@SuiteClasses({TaskServiceTest.class, TaskServiceTheoryTest.class, 
	ToDoControllerTest.class, ToDoServiceTest.class})
public class TestSuite {

}
