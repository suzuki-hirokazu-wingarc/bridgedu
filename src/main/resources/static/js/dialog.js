const message = "本当に削除しますか"
$('.has-delete-dialog').click(function(){
    if(!confirm(message)){
        return false;
    }
});
