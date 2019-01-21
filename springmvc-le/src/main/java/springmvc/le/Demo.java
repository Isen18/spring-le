package springmvc.le;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.nio.charset.Charset;
import javax.servlet.Servlet;

/**
 * @author Isen
 * @date 2019/1/7 23:16
 * @since 1.0
 */
public class Demo {
    Servlet servlet;

    public static void main(String[] args) {
        /**
         * 创建一个插入对象为一亿，误报率为0.01%的布隆过滤器
         */
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 100000000, 0.0001);
        bloomFilter.put("121");
        bloomFilter.put("122");
        bloomFilter.put("123");
        System.out.println(bloomFilter.mightContain("121"));
    }
}
