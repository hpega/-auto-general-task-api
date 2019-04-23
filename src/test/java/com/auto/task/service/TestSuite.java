package com.auto.task.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.auto.task.api.ToDoControllerTest;

@RunWith(Suite.class)
@SuiteClasses({TaskServiceTest.class, TaskServiceTheoryTest.class, 
	ToDoControllerTest.class, ToDoServiceTest.class})
public class TestSuite {

}
