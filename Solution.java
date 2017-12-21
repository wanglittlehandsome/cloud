package org.spring.springboot.dubbo;



import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class Solution<T> {

    //    int st, end;
//
//    public String longestPalindrome(String s) {
////        st = 0;
////        end = 0;
//        int len = s.length();
//        if (len <= 1) return s;
//        char[] chars = s.toCharArray();
//        for (int i = 0; i < len; i++) {
//            helper(chars, i, i);
//            helper(chars, i, i + 1);
//        }
//        return s.substring(st, end + 1);
//    }
//
//    private void helper(char[] chars, int l, int r) {
//        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
//            --l;
//            ++r;
//        }
//        if (end - st < r - l - 2) {
//            st = l + 1;
//            end = r - 1;
//        }
//    }

    public String longestPalindrome(String s) {
        Map map = new HashMap();
        map.put("aaa","123");
        map.get("aaa");
        int[] aa=new int[10];


        MapTest test= new MapTest() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public Object get(Object key) {
                return null;
            }

            @Override
            public Object put(Object key, Object value) {
                return null;
            }
        };
        //MapTest aa=new ()

        int len = s.length();
        if (len <= 1) return s;
        int st = 0, end = 0;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
            for (int j = 0; j < i; j++) {
                if (j + 1 == i) {
                    dp[j][i] = chars[j] == chars[i];
                    System.out.print("j:" + chars[j] + ",i:" + chars[i]);
                } else {
                    dp[j][i] = dp[j + 1][i - 1] && chars[j] == chars[i];
                }
                if (dp[j][i] && i - j > end - st) {
                    st = j;
                    end = i;
                }
            }
        }
        return s.substring(st, end + 1);
    }

   // public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.longestPalindrome("babad"));
//        System.out.println(solution.longestPalindrome("cbbd"));


   // }
   public static void main(String[] args) {
       String host = "https://ali-weather.showapi.com";
       String path = "/spot-to-weather";
       String method = "GET";
       String appcode = "eba7428326af4daea82c738675b3669f";
       Map<String, String> headers = new HashMap<String, String>();
       //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
       headers.put("Authorization", "APPCODE " + appcode);
       Map<String, String> querys = new HashMap<String, String>();
       querys.put("area", "泰山");
       querys.put("need3HourForcast", "0");
       querys.put("needAlarm", "0");
       querys.put("needHourData", "0");
       querys.put("needIndex", "0");
       querys.put("needMoreDay", "0");
       try {
           /**
            * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
            */
           HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
           System.out.println(response.toString());
           //获取response的body
           System.out.println(EntityUtils.toString(response.getEntity()));
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
