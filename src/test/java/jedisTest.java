//import com.D5.domain.User;
//import com.D5.mapper.DataMapper;
//import com.D5.service.IDataService;
//import com.D5.service.impl.DataService;
//import redis.clients.jedis.Jedis;
//
//import java.io.IOException;
//import java.util.*;
//
//public class jedisTest {
//    public static void main(String[]args) throws IOException {
//        Jedis jedis=new Jedis("202.116.163.203",6379);
//        jedis.auth("13800138000");
//        IDataService dataService=new DataService();
//       int i= dataService.selectDataCount();
//       System.out.println(i);
//     }
//}
