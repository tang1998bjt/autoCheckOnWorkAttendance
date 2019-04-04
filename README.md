# autoCheckOnWorkAttendance
原理是在service启动了一个线程 然后定时轮询是否需要打卡.  
目前在公司用着挺稳定的.  
如果有不懂的提Issues  
钉钉手机自动打卡.  
使用须知:  
1.安卓手机,需要root权限.  
2.手机分辨率1920*1080.如果需要使用其他分辨率 请自行修改.  
3.需要手机不关屏幕,建议买个二奶机连接充电线在公司对手机长期充电  
3.修改类在com.ojama.myapplication.service.HonestService中的hoest函数,对打卡的实现.  
4.具体实现轮询的线程为com.ojama.myapplication.service.JusticeThread,里面的run函数.
5.本人对安卓开发并不了解,全程通过一边谷歌一遍摸索,如有代码写的不太好看 请谅解!  
