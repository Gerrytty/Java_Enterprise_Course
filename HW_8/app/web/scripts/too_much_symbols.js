var ok = true;

var f = function () {
    ok = document.getElementById('comment').value.length < 100;
    if (!ok) {
        document.getElementById('too_much_symbols').style.color = 'red';
        document.getElementById('too_much_symbols').innerHTML = 'Too much symbols';
    }
    else {
        document.getElementById('too_much_symbols').innerHTML = '';
    }
    var key = event.keyCode;
    if(key === 8 || key === 37 || key === 39) {
        ok = true;
    }
};

var check = function () {
    document.getElementById('comment').readOnly = !ok;
};