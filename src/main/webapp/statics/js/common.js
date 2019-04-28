
function FirstVertify() {
    $("#passwordMessage").html("");
    var password = $("#password").val();
    var passwordagain = $("#passwordAgain").val();
    if (password != passwordagain && passwordagain != "") {
        $("#passwordMessage").html("两次输入的密码不一致");
    }
}