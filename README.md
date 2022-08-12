# VerticalViewPager实现自动滑动效果
代码比较简单，核心就是重写了viewpager，让它竖向滑动，然后加了一些动画效果。<br />
如果对原码有啥不懂，可以加QQ：3495268188 问我一下。<br />
总之就是很简单的加载一些网络图片，不过处理滑动问题有个地方还没有解决，就是我用反射改了drawlayout的scroller，把drawlayout的滑动范围改为了全屏任何位置右滑都出现，这样就出现了一个bug，长按也会弹出来，看过网上的解决办法，都是把drawlayout的源码复制，改一份，比较麻烦，懒的搞了，反正不太影响。<br />
还有就是viewpager使用setCurrentItem滑动的时间设置，这个我也用反射改了源码，不然后设置自动滑动的时候调用setCurrentItem方法一下就会滑动过去，动画效果都看不到了。<br />
## 效果图如下：
![image](https://github.com/MoonShadow66/YuanShenAlbum/blob/main/gif/yuanshen.gif)



