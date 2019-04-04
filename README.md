# autoCheckOnWorkAttendance
钉钉手机自动打卡.
使用须知:
1.安卓手机,需要root权限.
2.手机分辨率1920*1080.如果需要使用其他分辨率 请自行修改.
3.需要手机不关屏幕,建议买个二奶机连接充电线在公司对手机长期充电
3.修改类在com.ojama.myapplication.service.HonestService中的hoest,对打卡的实现.

原理是在service启动了一个线程 然后定时轮询是否需要打卡.
目前在公司用着挺稳定的.
如果有不懂的提Issues
