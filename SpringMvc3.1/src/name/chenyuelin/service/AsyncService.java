package name.chenyuelin.service;

import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
	public static final Log LOG = LogFactory.getLog(AsyncService.class);
	
	@Async
	public Future<Integer> getRandomNum() {
		try {
			Thread.sleep(1000 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int randomNum = (int) (Math.random() * 100);
		return new AsyncResult<Integer>(randomNum);
	}

	@Async
	public void printRandomNum() {
		try {
			Thread.sleep(1000 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Math.random() * 100);
	}
}
