package ie.cit.adf;

import ie.cit.adf.service.SchedulerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class SchedulerServiceIntegrationTest {
	@Autowired
	SchedulerService tested;
	
	@Test
	public void testCreateTask(){
		System.out.println(tested);
	}
}