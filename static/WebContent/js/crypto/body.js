/**
 * 
 */
var salt = CryptoJS.lib.WordArray.random(128 / 8);
console.info("WordArray.random\t" + salt.toString(CryptoJS.enc.Hex));
var md5 = CryptoJS.algo.MD5.create();
md5.update("Message Part 1");
md5.update("Message Part 2");
md5.update("Message Part 3");
console.info("md5\t" + md5.finalize().toString(CryptoJS.enc.Hex));

var key = CryptoJS.enc.Hex.parse('C00DEC4C2F261F4AF38A113EA544FA697A7837D60F8A96EA8DC53C0D5D2C1B91');
key = "³ÂÔÀÁÖ";
var iv = CryptoJS.enc.Hex.parse('892cfe81b33687889db438fdb33cef6c');//892cfe81b33687889db438fdb33cef6c
iv = CryptoJS.lib.WordArray.random(128 / 8);
//var salt = CryptoJS.lib.WordArray.random(64/8);
var encryptMode = {
	iv : iv,
	mode : CryptoJS.mode.ECB,
	padding : CryptoJS.pad.NoPadding
};
var encrypted = CryptoJS.AES.encrypt("³ÂÔÀ÷ë", key, encryptMode);
console.info("³ÂÔÀ÷ëencrypt\t" + encrypted.toString());
console.info("key:\t" + encrypted.key.toString(CryptoJS.enc.Hex));
console.info("iv:\t" + encrypted.iv.toString(CryptoJS.enc.Hex));
//console.info("salt:\t"+encrypted.salt.toString(CryptoJS.enc.Hex));
console.info("ciphertext:\t" + encrypted.ciphertext.toString());

var words = CryptoJS.AES.decrypt(encrypted, key, encryptMode);
console.info("³ÂÔÀ÷ëdecrypt\t" + words.toString(CryptoJS.enc.Utf8));
//var wordArray = CryptoJS.lib.WordArray.create("ÄãºÃ");
//alert("Random WordArray" +"\nHex: "+ wordArray + "\n\nBase 64: " + CryptoJS.enc.Base64.stringify(wordArray) +"\n\nLatin 1: " + CryptoJS.enc.Latin1.stringify(wordArray) + "\n\nUTF-8: " + CryptoJS.enc.Utf8.stringify(wordArray) + "\n\nUTF-16: " + CryptoJS.enc.Utf16.stringify(wordArray));
var hash = CryptoJS.SHA1("Message");

var words = CryptoJS.enc.Hex.parse('47cb80c8f59cdf411178ffc1711965e0');
var hex = CryptoJS.enc.Hex.stringify(words);
var words = CryptoJS.enc.Base64.parse('SGVsbG8sIFdvcmxkIQ==');
var base64 = CryptoJS.enc.Base64.stringify(words);

var words = CryptoJS.enc.Latin1.parse('Hello, World!');
var latin1 = CryptoJS.enc.Latin1.stringify(words);

/*
var encrypted = CryptoJS.AES.encrypt("Message", "Secret Passphrase");

var decrypted = CryptoJS.AES.decrypt(encrypted, "Secret Passphrase");

 alert("value:"+encrypted+"\nkey:"+encrypted.key+"\niv:"+encrypted.iv+"\nsalt:"+encrypted.salt+"\nciphertext:"+encrypted.ciphertext);
 alert(CryptoJS.enc.Hex.stringify(encrypted.key));
 alert(encrypted.ciphertext.toString(CryptoJS.enc.Hex));
 alert(decrypted.toString(CryptoJS.enc.Utf8)); */