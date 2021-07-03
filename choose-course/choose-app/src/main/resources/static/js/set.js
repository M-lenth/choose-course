// 设置自定义select选中
function set_select_checked(selectId, checkValue) {
    console.log(checkValue)
    var select = document.getElementById(selectId)
    for (var i = 0; i < select.length; i++) {
        if (select.options[i].value == checkValue) {
            select.options[i].selected = true;
            break;
        }
    }
}
