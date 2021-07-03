function time(id) {
    setInterval(function () {
        var time = new Date();
        var year = time.getFullYear(); //获取年份
        var month = time.getMonth() + 1; //获取月份
        var day = time.getDate();  //获取日期
        var hour = checkTime(time.getHours());  //获取时
        var minute = checkTime(time.getMinutes()); //获取分
        var second = checkTime(time.getSeconds()); //获取秒
        /****当时、分、秒、小于10时，则添加0****/
        function checkTime(i) {
            if (i < 10) return "0" + i;
            return i;
        }

        var $box = $("#"+id);
        $box.html(year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second);
        var hello;
        if(hour < 6)
            hello = "&emsp;&emsp;凌晨好"
        else if(hour < 12)
            hello = "&emsp;&emsp;上午好"
        else if(hour < 18)
            hello = "&emsp;&emsp;下午好"
        else
            hello = "&emsp;&emsp;晚上好"
        $box.append(hello)
    }, 1000);   //setInterval(fn,i) 定时器，每隔i秒执行fn
}