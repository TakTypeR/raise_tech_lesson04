const DELEATE_MESSAGE = "削除してもよろしいですか？"
//　基本フォーマット
// $("Selector").Method();
// Selector: html内のオブジェクトを指定 Method: サービスを呼び出し
// a#delete_target: AND指定 tag=a and id=delete_target
// function(){}: ラムダ式の様に無名関数をその場で定義して渡す
$("a#delete_target").click(function(){
    if(!confirm(DELEATE_MESSAGE))
    {
        return false;
    }
});
