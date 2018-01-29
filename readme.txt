简要说明：
	实现功能包括：
		1、登录微博、并且记录登录之后的cookie
		2、打开微博指定的页面，点击当前页面每一条微博的评论按钮，进行评论，并且点击关闭。
部署要点：
	1、ubuntu操作系统
	2、firefox火狐浏览器52.0版本以上
	3、geckodriver-0.19.1，下载地址：https://github.com/mozilla/geckodriver/releases，驱动文件位置：/Users/songk/WorkTools/
	4、JDK1.8以上
	5、部署端口默认为8003，可以在配置文件中修改
	6、部署成功之后，由于seleinum有时候关闭不掉浏览器，需要手动关闭，所以需要增加自动关闭浏览器的定时任务（cron任务），根据时间自定义实现，默认是5个小时执行一次：
	    #!/bin/bash
        ID=`ps -ef | grep "firefox" | grep -v "grep" | awk '{print $2}'`
        echo $ID

        echo "---------------"
        for id in $ID
        do
        kill -9 $id
        echo "killed $id"
        done
        echo "---------------"
	
