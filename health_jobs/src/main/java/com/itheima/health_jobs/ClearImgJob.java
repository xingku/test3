package com.itheima.health_jobs;

import com.itheima.Utils.QiniuUtils;
import com.itheima.constant.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;
    public void fun(){
        /*两个相减，得到垃圾图片 :垃圾图片-图片=垃圾图片*/
        System.out.println("删除了");
        Set<String> sdiff = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (sdiff!=null&&sdiff.size()>0){
            for (String s : sdiff) {
                /*先删除七牛云*/
                System.out.println("任务被执行了");
                QiniuUtils.deleteFileFromQiniu(s);
                /*再删除redis*/
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,s);
            }

        }

    }
}
