var validator = {};

string_to_date = function(cstring) {
    cstring = cstring + "";
    var fullyear = cstring.substring(0, 4);
    var month = cstring.substring(4, 6);
    var day = cstring.substring(6, 8);
    //
    var tmpdate = new Date(fullyear, month - 1, day)

    return tmpdate;
}

get_days = function(fdate, edate) {
	var date1 = string_to_date(fdate);
	var date2 = string_to_date(edate);
	var days = (date2.getTime() - date1.getTime()) / 86400000 + 1;
	return days;
}

var clearString = function(obj) {
	var len, str = "";

	for (len = obj.value.length; len > 0; len = len - 1) {
		str = str + "8";
	}
	obj.value = str;
}
// valid 1~100; 20170425:litai 0~六位数的检验;
validator.is_price = function(value, min, max) {
	// 1: define rules
	var Regex = /^(0|[1-9][0-9]*)+(\.[0-9]{2})?$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(1, min))
		return false;
	if (!validator.is_less(1, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid 1~100; 20170425:litai 可以输入0-100或者1.00-100.00;
validator.is_percent = function(value, min, max) {
	// 1: define rules
	var Regex = /^(?:100|[0-9]\d?)(?:\.00)?$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(1, min))
		return false;
	if (!validator.is_less(1, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
validator.is_greater = function(length, minLength) {
	return (minLength > 0) ? (length >= minLength) : true;
}
validator.is_less = function(length, maxLength) {
	return (maxLength > 0) ? (length <= maxLength) : true;
}

//valid scope; 20170516:jpp;
validator.is_scope = function(value, min, max) {
	//1: define rules
    var Regex = /^[0-9]{1,}-[0-9]{1,}$/,
		r = new RegExp(Regex),
		l = value.length;
		
	//2: verify length
 	if (!validator.is_greater(l, min))
		return false;
 	if (!validator.is_less(l, max))
		return false;
	
	//3: verify format
    return (r.test(value)) ? true : false;
}

/** 正则表达式验证需要严格按照以下格式书写 */
// valid length; 20160915:jpp;
validator.is_length = function(value, min, max) {
	// 1: define rules
	var l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return true;
}
// valid mobile phone; 20160915:jpp;
validator.is_phone = function(value, min, max) {
	// 1: define rules
	var Regex = /^1\d{10}$|^861\d{10}$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid telephone; 20160917:jpp;
validator.is_telephone = function(value, min, max) {
	// 1: define rules
	var Regex = /^0\d{2}-\d{7}|^0\d{3}-\d{7}|^0\d{2}-\d{8}|^0\d{3}-\d{8}|^0\d{2}\d{7}|^0\d{3}\d{7}|^0\d{2}\d{8}|^0\d{3}\d{8}/, r = new RegExp(
			Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid password; 20160915:jpp;
validator.is_password = function(value, min, max) {
	// 1: define rules
	var Regex = /^[a-zA-Z0-9_]{1,}$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid name; 20160915:jpp;
validator.is_name = function(value, min, max) {
	// 1: define rules
	var Regex = /^[a-zA-Z0-9\u4e00-\u9fa5]{1,}$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid integer; 20160915:jpp;
validator.is_integer = function(value, min, max) {
	// 1: define rules
	var Regex = /^[0-9]{1,}$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid positive negative integer; 20161010:jpp;
validator.is_pn_integer = function(value, min, max) {
	// 1: define rules
	var Regex = /^-?[0-9]{1,}$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid float; 20160921:jpp;
validator.is_float = function(value, min, max) {
	// 1: define rules
	var Regex = /^\d+\.?\d*$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid idcard; 20160916:jpp;
validator.is_idcard = function(value, min, max) {
	// 1: define rules
	var Regex = /^[a-zA-Z0-9]{1,}$/, r = new RegExp(Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}
// valid email; 20160916:jpp;
validator.is_email = function(value, min, max) {
	// 1: define rules
	var Regex = /^[^@]+@[^@.]+\.[^@]*\w\w$/, Regex2 = /[\(\)\<\>\,\;\:\\\"\[\]]/, r = new RegExp(
			Regex), r2 = new RegExp(Regex2), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value) || r2.test(value)) ? true : false;
}
// valid date; 201601206:jpp;
validator.is_date = function(value, min, max) {
	// 1: define rules
	var Regex = /^20[1-9][0-9]((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/, r = new RegExp(
			Regex), l = value.length;

	// 2: verify length
	if (!validator.is_greater(l, min))
		return false;
	if (!validator.is_less(l, max))
		return false;

	// 3: verify format
	return (r.test(value)) ? true : false;
}

validator.ltrim = function(s) {
	return s.replace(/(^\s*)/g, '');
}
validator.rtrim = function(s) {
	return s.replace(/(\s*$)/g, '');
}
// value of field with whitespace trimmed off
validator.trim = function(s) {
	return validator.rtrim(validator.ltrim(s));
}
/** new validator end */

var getStringLength = function(s) {
	/*
	 * var totalLength = 0, i, charCode;
	 * 
	 * for (i = 0; i < s.length; i++) { charCode = s.charCodeAt(i); if (charCode <
	 * 0x007f) { totalLength += 1; } else if ((0x0080 <= charCode) && (charCode <=
	 * 0x07ff)) { totalLength += 2; } else if ((0x0800 <= charCode) && (charCode <=
	 * 0xffff)) { totalLength += 3; } }
	 * 
	 * return totalLength;
	 */
	return s.length;
}

// for username & password, allow only letters and numbers
validator.is_token = function(val, minLength, maxLength) {
	var illegalChars = /[\W_]/;
	var l = getStringLength(val);

	if (!validator.is_greater(l, minLength))
		return false;
	if (!validator.is_less(l, maxLength))
		return false;

	return (illegalChars.test(val)) ? false : true;
}
// for time
validator.is_valid_time = function(val) {
	var l = getStringLength(val);
	var m = /(([0-1][0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]/;
	if (!validator.is_greater(l, 8))
		return false;
	if (!validator.is_less(l, 8))
		return false;

	return m.test(tval);
}
//

validator.is_valid_birth = function(val) {
	var tval = trim(val);
	var l = getStringLength(val);
	var m = /^(19|20)\d{6}$/;

	if (!validator.is_greater(l, 8))
		return false;
	if (!validator.is_less(l, 8))
		return false;

	return m.test(tval);
}
// for domain name
validator.is_valid_domain = function(val, minLength, maxLength) {
	var tval = trim(val);
	var l = getStringLength(val);

	if (!validator.is_greater(l, minLength))
		return false;
	if (!validator.is_less(l, maxLength))
		return false;
	//
	// ??????
	//

	return true;
}
// for host name
validator.is_valid_hostname = function(val, minLength, maxLength) {
	var tval = trim(val);
	var l = getStringLength(val);

	if (!validator.is_greater(l, minLength))
		return false;
	if (!validator.is_less(l, maxLength))
		return false;
	//
	// ??????
	//

	return true;
}
// for ip address
validator.is_valid_ip = function(val, minLength, maxLength) {
	var tval = trim(val);
	var l = getStringLength(val);

	if (!validator.is_greater(l, minLength))
		return false;
	if (!validator.is_less(l, maxLength))
		return false;
	//
	// ??????
	//

	return true;
}
// for site
validator.is_site = function IsURL(str_url) {
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
			+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp\u7684user@
			+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP\u5f62\u5f0f\u7684URL-
			// 199.194.52.184
			+ "|" + "(\.+?[a-zA-Z0-9])" + "|" // \u5141\u8bb8IP\u548cDOMAIN\uff08\u57df\u540d\uff09
			+ "([0-9a-z_!~*'()-]+\.)*" // \u57df\u540d- www.
			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // \u4e8c\u7ea7\u57df\u540d
			+ "[a-z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // \u7aef\u53e3- :80
			+ "((/?)|" // a slash isn't required if there is no file name
			+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	var re = new RegExp(strRegex);
	// re.test()
	if (re.test(str_url)) {
		return (true);
	} else {
		return (false);
	}
}
/*
 * http://www.aspxhome.com http://www.aspxhome.com/index.htm http://aspxhome.com
 * www.aspxhome.com http://2007@www.aspxhome.com ftp://www.aspxhome.com
 */

validator.is_name_char = function(val, minLength, maxLength) {
	var tval = trim(val);
	// var illegalChars= /\w*/;
	var illegalChars = /^[a-zA-Z0-9_]{1,60}$/;
	var l = getStringLengthChar(val);
	if (!validator.is_greater(l, minLength))
		return false;
	if (!validator.is_less(l, maxLength))
		return false;
	return (illegalChars.test(val)) ? true : false;
	// return (illegalChars.test(val)) ? false : true;
}

validator.is_name_cn = function(val, minLength, maxLength) {
	var tval = trim(val);
	// var illegalChars= /\w*/;
	var illegalChars = /^[a-zA-Z0-9_\u4e00-\u9fa5]{1,60}$/;
	var l = getStringLength(val);
	if (!validator.is_greater(l, minLength))
		return false;
	if (!validator.is_less(l, maxLength))
		return false;
	return (illegalChars.test(val)) ? true : false;
	// return (illegalChars.test(val)) ? false : true;
}

validator.is_valid_postcode = function IsURL(str_url) {
	var strRegex = /^[1-9]{1}(\d+){5}$/;
	var re = new RegExp(strRegex);
	if (re.test(str_url)) {
		return (true);
	} else {
		return (false);
	}
}

validator.is_valid_ID = function IsURL(str_url) {
	var strRegex = /^[1-9]((\d{14})|(\d{17})|(\d{16}[xX]))$/;
	var re = new RegExp(strRegex);
	if (re.test(str_url)) {
		return (true);
	} else {
		return (false);
	}
}

/*
 * validator.is_empty = function(val, minLength, maxLength) { return (val.length <
 * 1) ? true : false; }
 * 
 * validator.is_literal = function(val, minLength, maxLength) { return
 * (val.length < 1) ? false : true; }
 * 
 * validator.is_digit = function(val, minLength, maxLength) { return (val.length <
 * 1 || isNaN(parseInt(val))) ? false : true; }
 */

// \u975e\u8d1f\u6d6e\u70b9\u6570, \u6b63\u6d6e\u70b9\u6570 + 0 ???
// \u6d6e\u70b9\u6570
// validator.is_ufloat = function(val, minLength, maxLength) {
//
// var re = /^(-?\\d+)(\\.\\d+)?$/;
//
// return re.test(val) ? true : false;
//
// return true;
// }
// \u975e\u8d1f\u6d6e\u70b9\u6570, \u6b63\u6d6e\u70b9\u6570 + 0 ???
// \u6d6e\u70b9\u6570
validator.is_ufloat1 = function(val, minLength, maxLength) {
	/*
	 * var re = /^(-?\\d+)(\\.\\d+)?$/;
	 * 
	 * return re.test(val) ? true : false;
	 */
	var re = /^\d+\.?\d*$/;
	var l = getStringLength(val);

	if (!validator.is_greater(l, minLength))
		return false;
	if (!validator.is_less(l, maxLength))
		return false;
	return re.test(val) ? true : false;
}

validator.is_ufloat = function(val, minLength, maxLength) {
	if (!val)
		return false;
	var str = validator.trim(val);
	if (str.length < 1)
		return false;

	var re = /^\d+\.?\d*$/;
	if (!re.test(str))
		return false;

	var d, f = parseFloat(str) * 10;
	d = parseInt(f.toFixed(0));
	return (d >= 0) && (d <= 1000) ? true : false;
}

validator.is_valid_budget = function(val, minLength, maxLength) {
	if (!validator.is_ufloat(val, minLength, maxLength))
		return false;

	var myNum = Number(val);

	if (myNum >= 10000000) {
		return false;
	}
	return true;
}

/*
 * http://hi.baidu.com/jxcjxing/blog/item/6d849958d1b8318a800a187d.html
 * 1.\u4e0b\u9762\u5217\u51fa\u4e86\u4e00\u4e9b\u5224\u8bfb\u6570\u503c\u7c7b\u578b\u7684\u6b63\u5219\u8868\u8fbe\u5f0f
 * 
 * "^\\d+$"\u3000\u3000//\u975e\u8d1f\u6574\u6570\uff08\u6b63\u6574\u6570 +
 * 0\uff09
 * 
 * "^[0-9]*[1-9][0-9]*$"\u3000\u3000//\u6b63\u6574\u6570
 * 
 * "^((-\\d+)|(0+))$"\u3000\u3000//\u975e\u6b63\u6574\u6570\uff08\u8d1f\u6574\u6570 +
 * 0\uff09
 * 
 * "^-[0-9]*[1-9][0-9]*$"\u3000\u3000//\u8d1f\u6574\u6570
 * 
 * "^-?\\d+$"\u3000\u3000\u3000\u3000//\u6574\u6570
 * 
 * "^\\d+(\\.\\d+)?$"\u3000\u3000//\u975e\u8d1f\u6d6e\u70b9\u6570\uff08\u6b63\u6d6e\u70b9\u6570 +
 * 0\uff09
 * 
 * "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$"\u3000\u3000//\u6b63\u6d6e\u70b9\u6570
 * 
 * "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$"\u3000\u3000//\u975e\u6b63\u6d6e\u70b9\u6570\uff08\u8d1f\u6d6e\u70b9\u6570 +
 * 0\uff09
 * 
 * "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$"\u3000\u3000//\u8d1f\u6d6e\u70b9\u6570
 * 
 * "^(-?\\d+)(\\.\\d+)?$"\u3000\u3000//\u6d6e\u70b9\u6570
 */

/*
 * function checkRate(input) { var re = /^[0-9]+.?[0-9]*$/;
 * //\u5224\u65ad\u5b57\u7b26\u4e32\u662f\u5426\u4e3a\u6570\u5b57
 * //\u5224\u65ad\u6b63\u6574\u6570 /^[1-9]+[0-9]*]*$/
 * 
 * if (!re.test(input.rate.value)) {
 * alert("\u8bf7\u8f93\u5165\u6570\u5b57(\u4f8b:0.02)"); input.rate.focus();
 * return false; } }
 */

var isArray = function(o) {
	return Object.prototype.toString.call(o) === '[object Array]';
}

// list: [ "some text1", "some text2", ... ]
if (typeof (window.getItem) != "function") {
	window.getItem = function(list, id) {
		if (!list)
			return -1;
		for (var i = 0, len = list.length; i < len; i++)
			if (list[i] == id)
				return i;

		return -1;
	}
}

// list: [ [1, "some text1"], [2, "some text2"], [3, "some text3"], ... ]
if (typeof (window.getListItem) != "function") {
	window.getListItem = function(list, id, idx) {
		if (!list)
			return null;
		var len = list.length;

		if (typeof (idx) == "undefined" || idx < 0)
			idx = 0;
		for (var i = 0; i < len; i++)
			if (list[i][idx] == id)
				return list[i];

		return null;
	}
}

if (typeof (window.getListObject) != "function") {
	// list: [ {id:id1, value:v1}, {id:id2, value:v2}, {id:id3, value:v3}, ... ]
	window.getListObject = function(list, id) {
		var len = list.length;

		for (var i = 0; i < len; i++)
			if (list[i].id == id)
				return list[i];

		return null;
	}
}

function iform(cols, form) {
	this.cols = cols;
	this.form = form;
}

iform.prototype = {
	init_object : function(rows) {
		var iobj, self = this;

		for (var i = 0, o, e, r; i < this.cols.length; i++) {
			o = this.cols[i];
			//
			e = document.getElementById(o.id);
			if (typeof (e) != "object" || e == null) {
				o.obj = null;
				continue;
			}
			o.obj = e; // 对应的DOM对象
			//
			r = this.find(rows, o.id);
			// ·Ç formÖÐµÄÔªËØ
			if (typeof (e.type) == "undefined") {
				if (r != null) {
					if (typeof (o.init) == "object") {
						if (typeof (o.init.formatter) == "function")
							e.innerHTML = o.init.formatter.call(this, r.value);
						else if (typeof (o.init.grid) == "function")
							o.init.grid.call(this, o.id, r.value);
						else if (typeof (o.init.action) == "function")
							o.init.action.call(this, o.id, r.value);
						else
							e.innerHTML = r.value;
					} else
						e.innerHTML = r.value;
				} else if (typeof (o.init) == "object") {
					if (typeof (o.init.formatter) == "function")
						e.innerHTML = o.init.formatter.call(this);
					if (typeof (o.init.action) == "function")
						o.init.action.call(this, e);
				}
				continue;
			}
			if (typeof (o.init) != "object")
				continue;
			iobj = o.init;
			//
			switch (e.type) {
			case "text":
			case "hidden":
				//
				if (r != null) {
					if ((typeof r.value == "boolean") && (!r.value))
						r.value = "";
					if (typeof (iobj.formatter) == "function")
						e.value = iobj.formatter.call(this, r.value);
					else
						e.value = r.value;
				} else if (typeof (iobj.vdefault) != "undefined")
					e.value = iobj.vdefault;
				//
				if (typeof (iobj.size) == "number")
					e.size = iobj.size;
				if (typeof (iobj.readOnly) == "boolean")
					e.readOnly = iobj.readOnly;

			case "password":
				if (e.type == "text" || e.type == "password") {
					if (typeof (o.tips) == "string")
						e.placeholder = o.tips;
					else if ((typeof (o.tips) == "object")
							&& (typeof (o.tips.text) == "string"))
						e.placeholder = o.tips.text;
				}
				//
				if (typeof (iobj.maxLength) == "number")
					e.maxLength = iobj.maxLength;
				//

				if (typeof (iobj.minLength) == "number")
					e.minLength = iobj.minLength;
				//
				if (typeof (iobj.size) == "number")
					e.size = iobj.size;
				//
				if (typeof (iobj.enable) == "boolean")
					e.disabled = (!iobj.enable);
				//

				if (typeof (iobj.allowNull) == "boolean")
					e.allowNull = iobj.allowNull;
				else
					e.allowNull = true;
				break;

			case "radio":
				if (typeof (iobj.name) == "undefined"
						|| typeof (iobj.value) == "undefined")
					break;
				//
				r = this.find(rows, iobj.name);
				//
				e.value = iobj.value;
				e.checked = false;
				//
				if (r != null) {
					if (r.value == e.value)
						e.checked = true;
				} else if (typeof (iobj.vdefault) == "boolean")
					e.checked = iobj.vdefault;
				//
				if (typeof (iobj.enable) == "boolean")
					e.disabled = (!iobj.enable);
				break;

			case "checkbox":
				if (typeof (iobj.value) == "undefined")
					break;
				//
				e.value = iobj.value;
				e.checked = false;
				//
				if ((r != null) && (r.value != 0)) {
					if (r.value == e.value)
						e.checked = true;
				} else if (typeof (iobj.vdefault) == "boolean") {
					e.checked = iobj.vdefault;
				}
				//
				if (typeof (iobj.enable) == "boolean")
					e.disabled = (!iobj.enable);
				break;

			case "select-one":
				// case "select-multiple":
				//
				if (typeof (iobj.action) == "function")
					iobj.action.call(this, e);
				else {
					//
					if (typeof (iobj.ds) == "function")
						iobj.ds = iobj.ds.call(this);
					//
					if (isArray(iobj.ds) && (typeof (iobj.value) == "number")
							&& (typeof (iobj.text) == "number")) {
						e.options.length = 0;
						for (var j = 0, d; j < iobj.ds.length; j++) {
							d = iobj.ds[j];
							e.options[j] = new Option(d[iobj.text],
									d[iobj.value]);
						}
					}
				}
				//
				if (r != null)
					e.value = r.value;
				else if (typeof (iobj.vdefault) != "undefined")
					e.value = iobj.vdefault;
				//
				if (typeof (iobj.size) == "number")
					e.size = iobj.size;
				else
					e.size = 1;
				//
				if (typeof (iobj.multiple) == "boolean")
					e.multiple = iobj.multiple;
				//
				if (typeof (iobj.enable) == "boolean")
					e.disabled = (!iobj.enable);
				break;

			case "textarea":
				//
				if (r != null) {
					if (typeof iobj.formatter == "function")
						e.value = iobj.formatter.call(this, r.value);
					else
						e.value = r.value;
				} else if (typeof (iobj.vdefault) != "undefined")
					e.value = iobj.vdefault;
				//
				if (typeof (iobj.readOnly) == "boolean")
					e.readOnly = iobj.readOnly;
				//
				if (typeof (iobj.cols) == "number")
					e.cols = iobj.cols;
				//
				if (typeof (iobj.rows) == "number")
					e.rows = iobj.rows;
				//
				if (typeof (iobj.enable) == "boolean")
					e.disabled = (!iobj.enable);
				//
				if (typeof (iobj.allowNull) == "boolean")
					e.allowNull = iobj.allowNull;
				else
					e.allowNull = true;
				break;

			case "file":
				/*
				 * if (typeof(iobj.name) == "undefined" || typeof(iobj.value) ==
				 * "undefined") break; // if (typeof(iobj.size) == "number")
				 * e.size = iobj.size;
				 */
				break;
			}
		}

		//
		for (var i = 0; i < this.cols.length; i++) {
			this.init_event(this.cols[i], "oninit");
			this.init_event(this.cols[i], "onclick");
			this.init_event(this.cols[i], "onchange");
			this.init_event(this.cols[i], "onkeyup");
		}
		//
		if (this.form != null) {
			this.form.onsubmit = function() {
				return self.submit();
				/*
				 * if (self.submit()) return true; else return false;
				 * //self.form.submit();
				 */
			}
		}
	},

	init_event : function(o, evt) {
		var eobj, e;

		e = o.obj;
		if (typeof (o.event) != "object" || e == null)
			return;
		//
		if (o.event.constructor == Array) {
			eobj = this.find(o.event, evt);
			if (eobj == null)
				return;
		} else if ((o.event.constructor == Object)
				&& (typeof (o.event.id) == "string") && (o.event.id == evt))
			eobj = o.event;
		else
			return;
		//
		if (typeof (eobj.fn) != "function")
			return;
		e[evt] = eobj.fn;
		//
		switch (e.type) {
		case "radio":
			if (!e.checked)
				return;
			break;

		case "button":
		case "checkbox":
			break;

		case "select-one":
		case "select-multiple":
			break;

		case "text":
		case "password":
		case "textarea":
		case "file":
		case "hidden":
			break;

		default:
			return;
		}
		//
		/*
		 * if (typeof(eobj.params) != "undefined") eobj.params = []; if
		 * (eobj.params.length < 1 || eobj.params[0] != e)
		 * eobj.params.unshift(e); //
		 */
		if (rows.length > 0) // 初始化的时候不执行，触发事件后执行
			eobj.fn.call(this, e);
	},

	do_event2 : function(o, evt, force) {
		var eobj, e;

		e = o.obj;
		if (typeof (o.event) != "object" || e == null)
			return;
		//
		if (o.event.constructor == Array) {
			eobj = this.find(o.event, evt);
			if (eobj == null)
				return;
		} else if ((o.event.constructor == Object)
				&& (typeof (o.event.id) == "string") && (o.event.id == evt))
			eobj = o.event;
		else
			return;
		//
		if (typeof (eobj.fn) != "function")
			return;
		if (typeof (eobj.defined_event) != "boolean") {
			e["on" + evt] = eobj.fn;
			eobj.defined_event = true;
		}
		//
		switch (e.type) {
		case "radio":
		case "checkbox":
			if (!force && !e.checked)
				return;
			break;

		case "select-one":
		case "select-multiple":
			break;

		case "text":
		case "password":
		case "textarea":
			break;

		default:
			return;
		}
		//
		if (typeof (eobj.params) != "undefined")
			eobj.params = [];
		if (eobj.params.length < 1 || eobj.params[0] != e)
			eobj.params.unshift(e);
		//
		eobj.fn.apply(this, eobj.params);
	},

	do_event : function(id, evt) {
		this.do_event2(this.find(this.cols, id), evt, true);
	},

	show_prompt : function(o, robj) {
		if ((typeof (o.tips) == "object")
				&& (typeof (o.tips.action) == "function")) {
			if (typeof (o.tips.text) == "string") {
				o.tips.action.call(o.obj, o.tips.text);
				o.obj.focus();
			} else if (typeof (o.tips.referer) == "string") {
				var e = document.getElementById(o.tips.referer);
				if (e)
					o.tips.action.call(e, robj);
			}
		}
		return false;
	},

	ajax_submit : function() {
		var eobj, str = [], val, idx;

		for (var i = 0, o; i < this.cols.length; i++) {
			o = this.cols[i];
			if (o.obj == null)
				continue;
			eobj = o.obj;
			if (eobj.disabled)
				continue;
			//
			switch (eobj.type) {
			case "password":
			case "textarea":
			case "hidden":
			case "text":
			case "file":
				if ((val = eobj.value) != null)
					str.push(eobj.name + '=' + encodeURIComponent(val));
				break;
			case "radio":
			case "checkbox":
				if (eobj.checked)
					str.push(eobj.name + '=' + encodeURIComponent(eobj.value));
				break;
			case "select-one":
				val = eobj.selectedIndex;
				if (val < 0)
					break;
				str.push(eobj.name + '='
						+ encodeURIComponent(eobj.options[val].value));
				break;
			case "select-multiple":
				break; // ??????
			}
		}

		return str.join("&");
	},

	submit : function() {
		var cobj = null, sobj = null;
		for (var i = 0, l = this.cols.length, o, e, min, max, eobj; i < l; i++) {
			o = this.cols[i];
			if (typeof (o.submit) != "object" || o.obj == null)
				continue;
			e = o.submit;
			if (typeof (e) != "object" || e == null)
				continue;
			eobj = o.obj;
			if (eobj.disabled)
				continue;

			// convert data format
			if (typeof (e.formatter) == "function") {
				switch (eobj.type) {
				case "hidden":
				case "password":
				case "text":
				case "textarea":
					eobj.value = e.formatter.call(this, eobj.value);
					break;
				}
			}

			// validate form field
			switch (eobj.type) {
			case "password":
			case "text":
				//
				if (eobj.value.length < 1) {
					if (typeof (o.init.allowNull) == "boolean") {
						if (!o.init.allowNull)
							return this.show_prompt(o);
					}
					break;
				}
				//
				if (typeof (e.check) == "function") {
					min = (typeof (o.init.minLength) == "number") ? o.init.minLength
							: -1;
					max = (typeof (o.init.maxLength) == "number") ? o.init.maxLength
							: -1;
					//
					if (!e.check.call(this, eobj.value, min, max))
						return this.show_prompt(o);
				}
				break;

			case "textarea":
				//
				if (eobj.value.length < 1) {
					if (typeof (o.init.allowNull) == "boolean") {
						if (!o.init.allowNull)
							return this.show_prompt(o);
					}
					break;
				}
				//
				if (typeof (e.check) == "function") {
					if (!e.check.call(this, eobj.value, -1, -1))
						return this.show_prompt(o);
				}
				break;

			case "file":
				if (eobj.files.length < 1) {
					if (typeof (o.init.allowNull) == "boolean") {
						if (!o.init.allowNull)
							return this.show_prompt(o);
					}
					break;
				}
				break;
			}

			// form check
			if (typeof (e.integrate) == "function")
				cobj = o;

			// form postform
			if (typeof (e.postform) == "function")
				sobj = o;
		}

		if (cobj) {
			var robj = {
				result : false
			};
			if (!cobj.submit.integrate.call(this, this.form, robj))
				return this.show_prompt(cobj, robj);
		}

		if (sobj) {
			if (typeof (sobj.submit.url) == "string")
				return sobj.submit.postform.call(this, this, sobj);
			else
				return sobj.submit.postform.call(this, this.form);
		} else
			return true;
	},

	config_ds : function(name, ds) {
		var obj = this.find(this.cols, name);

		if (obj && (typeof (obj.init) == "object")) {
			obj.init.ds = ds;
		}
	},

	/*
	 * window.obj = new iform();
	 * 
	 * obj.change_attr("password2", "allowNull", true);
	 */
	change_attr : function(id, aname, avalue) {
		var obj = this.find(this.cols, id);
		if (obj && (typeof (obj.init) == "object")) {
			obj.init[aname] = avalue;
		}
	},

	find : function(arr, name) {
		if (isArray(arr)) {
			var obj, nr = arr.length;

			for (var i = 0; i < nr; i++) {
				obj = arr[i];
				if ((typeof (obj.id) == "string") && (obj.id == name))
					return obj;
			}
		} else if ((typeof arr == "object") && (arr != null)) {
			if (typeof arr[name] != "undefined")
				return {
					id : name,
					value : arr[name]
				};
		}

		return null;
	}
}

var get_error_text = function(ecode) {
	var lang, getLanguage = function() {
		var v1 = window.location.href.split("?"), v2 = v1[0].split("/"), l = v2.length - 2;
		return (l < 0) ? "en_US" : v2[l];
	};
	String.prototype.str2kv = function() {
		return this.split("=====");
	}
	String.prototype.str2array = function() {
		var v = this.split("&&&&&"), d = [];
		for (var i = 0, l = v.length; i < l; i++)
			d.push(v[i].str2kv());
		return d;
	}
	String.prototype.str2lang = function() {
		var v = this.split("+++++-----");
		return [ v[0], v[1], v[2].str2array() ];
	}
	String.prototype.str2dict = function() {
		var v = this.split("*****!!!!!"), d = [];
		for (var i = 0, l = v.length; i < l; i++)
			d.push(v[i].str2lang());
		return d;
	}
	if (localStorage && (lang = localStorage.getItem("lang"))) {
		var l, ls = getListItem(lang.str2dict(), getLanguage());
		if (ls && (l = getListItem(ls[2], ecode)))
			return l[1];
	}
	if (ecode)
		return [ "Error code = ", ecode ].join("");
	return null;
}

/**
 * for dialog
 */
submitDialog = function(iobj, fobj) {
	var form = iobj.form;
	var params = iobj.ajax_submit();
	var req = new XMLHttpRequest();
	var done = false, robj;

	req.open('POST', fobj.submit.url, false);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.send(params);
	if ((req.readyState == 4) && (req.status == 200)
			&& (req.responseText.length > 0)) {
		robj = eval('(' + req.responseText + ')');
		if (typeof (robj) == "object") {
			if (robj.result) {
				window.returnVal = {
					cancel : false,
					action : robj
				};
				window.parent.hidePopWin(true);
				return false;
			} else
				done = true;
		}
	}
	if (!done)
		robj = {
			result : false,
			ecode : 2
		};

	return iobj.show_prompt(fobj, robj);
}

/**
 * for window
 */
submitWindow = function(iobj, fobj) {
	var form = iobj.form;
	var req = new XMLHttpRequest();
	var done = false, robj, file = typeof (fobj.submit.file);

	if (file != "boolean" || !fobj.submit.file) {
		var params = iobj.ajax_submit();
		req.open('POST', fobj.submit.url, false);
		req.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		req.send(params);
	} else {
		var formdata = new FormData(form);
		req.open('POST', fobj.submit.url, false);
		req.send(formdata);
	}
	if ((req.readyState == 4) && (req.status == 200)
			&& (req.responseText.length > 0)) {
		robj = eval('(' + req.responseText + ')');
		if (typeof (robj) == "object")
			done = true;
	}

	if (!done)
		robj = {
			result : false,
			emsg : "网络错误！"
		};
	if (robj.result) {
		var ref;
		if (typeof (robj.url) == "string") {
			ims_open(robj.url, true, true);
			return false;
		}
		ref = typeof fobj.submit.refresh;
		if (ref != "undefined") {
			if (ref == "boolean") {
				if (fobj.submit.refresh)
					ims_close(true);
			} else if (ref == "string") {
				ims_close();
				ims_close();
				ims_open(fobj.submit.refresh);
			} else if (ref == "function") {
				if (robj.msg) {
					fobj.submit.refresh(robj.data, robj.msg);
				} else {
					fobj.submit.refresh(robj.data);
				}

				return false;
			}
		} else if (fobj.submit.back) {
			ims_close();
			return false;
		}
		if (fobj.reset)
			form.reset();
		if (fobj.submit.prompt)
			return fobj.submit.prompt(robj);
		return false;
	} else if (fobj.submit.prompt)
		return fobj.submit.prompt(robj);

	return iobj.show_prompt(fobj, robj);
}

submitWXAjax = function(iobj, fobj) {
	var form = iobj.form;
	var params = iobj.ajax_submit();
	var req = new XMLHttpRequest();
	var robj;

	req.open('POST', fobj.submit.url, false);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.send(params);
	if ((req.readyState == 4) && (req.status == 200)
			&& (req.responseText.length > 0))
		robj = eval('(' + req.responseText + ')');
	else
		robj = {
			result : false,
			msg : "ÍøÂçŽíÎó£¡"
		};

	var sm = fobj.submit;
	if (robj.result) {

		if (typeof sm.refresh == "boolean") {
			if (sm.refresh) {
				window.location.reload();
				return false;
			}
		}

		if (typeof sm.back == "number") {
			window.history.go(-sm.back);
			return false;
		}

		if (typeof sm.replace == "boolean") {
			if (sm.replace) {
				window.location.replace(document.referrer);
				return false;
			}
		}

		if (typeof sm.reset == "boolean") {
			if (sm.replace) {
				form.reset();
				return false;
			}
		}

	} else {
		if (typeof sm.error == "function")
			sm.error.call(this, robj.msg);
		else
			alert(robj.msg);
	}
	return false;
}

function submitPostAjax(url) {
	var req = new XMLHttpRequest();
	var done, robj;
	req.open('POST', url, false);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.send();
	if ((req.readyState == 4) && (req.status == 200)
			&& (req.responseText.length > 0)) {
		robj = eval('(' + req.responseText + ')');
		if (robj.result) {
			alert("Ok");
			window.location.href = [ "http://wx.enjoyclick.cn/lib/mch_entry.php" ]
					.join("");
		} else
			alert(robj.msg);
	}
	return done;
}

/**
 * for SNS
 */
submitSNS = function(iobj, fobj) {
	var form = iobj.form;
	var params = iobj.ajax_submit();
	var req = new XMLHttpRequest();
	var done = false, robj;

	req.open('POST', fobj.submit.url, false);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.send(params);
	if ((req.readyState == 4) && (req.status == 200)
			&& (req.responseText.length > 0)) {
		robj = eval('(' + req.responseText + ')');
		if (robj.result)
			done = true;
	}
	if (!done) {
		alert(robj.msg);
		// robj = {result:false,ecode:2};
	}
	if (robj.result) {
		if (typeof (robj.url) == "string") {
			window.location.href = robj.url;
			return false;
		}
		if (fobj.submit.refresh) {
			ims_close(true);
		} else if (fobj.submit.back) {
			ims_close();
		}
		if (fobj.reset)
			form.reset();
		if (fobj.submit.prompt)
			return fobj.submit.prompt(robj);
	} else if (fobj.submit.prompt)
		return fobj.submit.prompt(robj);

	return iobj.show_prompt(fobj, robj);
}

/**
 * for Ajax-sync {result:true, url:'...' or value:...} or {result:false,
 * ecode:errcode, emsg:...}
 */
submitAjax = function(url, params) {
	var req = new XMLHttpRequest();
	var done = false, robj;

	if (params) {
		req.open('POST', url, false);
		req.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		req.send(params);
	} else {
		req.open('GET', url, false);
		req.send();
	}
	if ((req.readyState == 4) && (req.status == 200)
			&& (req.responseText.length > 0)) {
		robj = eval('(' + req.responseText + ')');
		if (typeof (robj) == "object")
			done = true;
	}
	if (!done)
		robj = {
			result : false,
			emsg : "网络错误或系统内部错误"
		};
	return robj;
}

var getStringLengthChar = function(s) {

	var totalLength = 0, i, charCode;

	for (i = 0; i < s.length; i++) {
		charCode = s.charCodeAt(i);
		if (charCode < 0x007f) {
			totalLength += 1;
		} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
			totalLength += 2;
		} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
			totalLength += 2;
		}
	}
	return totalLength;

	// return s.length;
}

/**
 * weui tips for submit result; 20160915:jpp
 */
var showWeuiTip = function(obj) {
	var d, dc = document;
	var addEvent = function(E, e, fn) {
		E.attachEvent ? E.attachEvent(e, function() {
			fn.call(E)
		}) : E.addEventListener(e.slice(2), fn, false);
	};
	var removeEvent = function(E, e, fn) {
		E.detachEvent ? E.detachEvent(e, fn) : E.removeEventListener(
				e.slice(2), fn);
	};

	// show result
	var toast = dc.getElementById("toast"), txt = dc.getElementById("txt");
	if (typeof (obj) == "object" && !obj.result) {
		txt.innerHTML = obj.emsg;
	} else if (typeof (obj) == "string")
		txt.innerHTML = obj + "有误";

	toast.style.display = "";
	setTimeout(function() {
		toast.style.display = "none";
	}, 2000);
}