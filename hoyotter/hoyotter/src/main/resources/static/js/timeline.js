let addComment = (e) => {
	e.preventDefault();
	let jsonString = {
		'userName': $('input[name=userName]').val(),
		'comment': $('input[name=comment]').val()
	};
	$.ajax({
		type: 'POST',
		url: '/addComment',
		data: JSON.stringify(jsonString),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		let ajaxComList = JSON.parse(result);
		let ajaxItem = $('.timeline').find('.ajaxItem');
		
		$(ajaxItem).children().remove();
		$('.item').children().remove();
		
		
		ajaxComList.forEach((ajaxCom, index) => {
			//above
			let userAndTime = $('<div />', { 'class': 'userAndTime'} );
			$('<p />', {  'class': 'userBtn' } ).appendTo(userAndTime);
			$('<p />', {  'class': 'name', 'text': ajaxCom.userName } ).appendTo(userAndTime);
			$('<p />', {  'class': 'time', 'text': ajaxCom.postedAt } ).appendTo(userAndTime);
			$('<p />', {  'class': 'userComment', 'text': ajaxCom.comment } ).appendTo(userAndTime);
			$(userAndTime).appendTo(ajaxItem);
		});
		$('input[name=comment]').val("");
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
};

let myProfile = (e) => {
	e.preventDefault();
	let jsonString = {
		'userId': $('#hiddenUserId').val(),
		'userName': $('input[name=hiddenUserName]').val(),
		'password': $('input[name=hiddenPassword]').val(),
		'profile': $('input[name=hiddenProfile]').val()
	};
	$.ajax({
		type: 'POST',
		url: '/mypage',
		data: JSON.stringify(jsonString),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		let myselfList = JSON.parse(result);
		let mypageAbove = $('#mypage').find('.above');
		
		$(mypageAbove).children().remove();
		
		
		myselfList.forEach((myself, index) => {
			//above
			let aboveDiv = $('<div />', { 'class': 'aboveDiv'} );
			$('<p />', {  'class': 'myImg' } ).appendTo(aboveDiv);
			$('<p />', {  'class': 'myId', 'text': myself.id } ).appendTo(aboveDiv);
			$('<p />', {  'class': 'myName', 'text': myself.userName } ).appendTo(aboveDiv);
			$('<p />', {  'class': 'myIntro', 'text': myself.profile } ).appendTo(aboveDiv);
			$('<button />', {  'id': 'editBtn', 'text': 'edit' } ).appendTo(aboveDiv);
			$(aboveDiv).appendTo(mypageAbove);
		});
		$("#mypage").dialog("open");
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
	
	$.ajax({
		type: 'POST',
		url: '/mypage_under',
		data: JSON.stringify(jsonString),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		let myselfList = JSON.parse(result);
		let mypageUnder = $('#mypage').find('.under');
		
		$(mypageUnder).children().remove();
		
		myselfList.forEach((myself, index) => {
			//bottom
			let myItem = $('<div />', { 'class': 'myItem'});
			$('<p />', {  'class': 'myNameUnder', 'text': myself.userName } ).appendTo(myItem);
			$('<p />', {  'class': 'myPostTime', 'text': myself.postedAt } ).appendTo(myItem);
			$('<p />', {  'class': 'myComment', 'text': myself.comment } ).appendTo(myItem);
			$(myItem).appendTo(mypageUnder);
		})
		$("#mypage").dialog("open");
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
};

let othersProfile = (e) => {
	$.ajax({
		type: 'POST',
		url: '/otherspage',
		data: JSON.stringify({ "othersUserName": $(e.target).next().val() }),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		let othersList = JSON.parse(result);
		let otherspageAbove = $('#othersPage').find('.aboveOthers');
		
		$(otherspageAbove).children().remove();
		
		
		othersList.forEach((others, index) => {
			//above
			
			$('<p />', {  'class': 'othersImg' } ).appendTo(otherspageAbove);
			$('<p />', {  'class': 'othersId', 'text': others.id } ).appendTo(otherspageAbove);
			$('<p />', {  'class': 'othersName', 'text': others.userName } ).appendTo(otherspageAbove);
			$('<p />', {  'class': 'othersIntro', 'text': others.profile } ).appendTo(otherspageAbove);
			
		});
		$("#othersPage").dialog("open");
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
	
	$.ajax({
		type: 'POST',
		url: '/otherspage_under',
		data: JSON.stringify({ "othersUserName": $(e.target).next().val() }),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		let othersList = JSON.parse(result);
		let otherspageUnder = $('#othersPage').find('.underOthers');
		
		$(otherspageUnder).children().remove();
		
		othersList.forEach((others, index) => {
			//bottom
			let othersItem = $('<div />', { 'class': 'othersItem'});
			$('<p />', {  'class': 'othersNameUnder', 'text': others.userName } ).appendTo(othersItem);
			$('<p />', {  'class': 'othersPostTime', 'text': others.postedAt } ).appendTo(othersItem);
			$('<p />', {  'class': 'othersComment', 'text': others.comment } ).appendTo(othersItem);
			$(othersItem).appendTo(otherspageUnder);
		})
		$("#othersPage").dialog("open");
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
};

let editProfile = (e) => {
	e.preventDefault();
	let jsonString = {
		"userId": $('#hiddenUserId').val(),
		"userName": $('input[name=hiddenUserName]').val(),
		"password": $('input[name=hiddenPassword]').val(),
		"profile": $('input[name=hiddenProfile]').val()
	};	
	$.ajax({
		type: 'POST',
		url: '/edit',
		data: JSON.stringify(jsonString),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		
		let myselfList = JSON.parse(result);
		let mypageAbove = $('#mypage').find('.above');
		
		$(mypageAbove).children().remove();
		
		
		myselfList.forEach((myself, index) => {
			//above
			let form = $('<form />', { 'class':'editForm', 'method': 'post', 'id': 'uploadForm', 'name': 'uploadForm',
			'th:object': '${uploadForm}', 'action': '', 'th:action': ''})
			$('<p />', {  'class': 'myImg' } ).appendTo(form);
			$('<input />', {  'class': 'myId', 'value': myself.id, 'id': 'userId', 'name': 'userIdEdit' } ).appendTo(form);
			$('<input />', {  'class': 'myPassword', 'value': myself.password,'id': 'password', 'name': 'passwordEdit' } ).appendTo(form);
			$('<input />', {  'type': 'text' ,'class': 'myNameEdit', 'value': myself.userName,'id': 'userName', 'name': 'userNameEdit' } ).appendTo(form);
			$('<input />', {  'type': 'text' ,'class': 'myIntroEdit', 'value': myself.profile, 'id': 'profile','name': 'profileEdit' } ).appendTo(form);
			$('<input />', {  'type': 'button' , 'id': 'doneBtn', 'value': 'done' } ).appendTo(form);
			$(form).appendTo(mypageAbove);
		});
		
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
};

let uploadProfile = (e) => {
	
	let jsonString = {
		'userIdEdit': $('input[name=userIdEdit]').val(),
		'userNameEdit': $('input[name=userNameEdit]').val(),
		'passwordEdit': $('input[name=passwordEdit]').val(),
		'profileEdit': $('input[name=profileEdit]').val()
	};	
	$.ajax({
		type: 'POST',
		url: '/upload',
		//?????????????????????
		data: JSON.stringify(jsonString),
		contentType: 'application/json',
		datatype: 'json',
		scriptCharset: 'utf-8'
	})
	.then((result) => {
		console.log(result);
		let myselfList = JSON.parse(result);
		let mypageAbove = $('#mypage').find('.above');
		$(mypageAbove).children().remove();
			//above
			myselfList.forEach((myself, index) => {
			let aboveDiv = $('<div />', { 'class': 'aboveDiv'} );
			$('<p />', {  'class': 'myImg' } ).appendTo(aboveDiv);
			$('<p />', {  'class': 'myId', 'text': myself.id } ).appendTo(aboveDiv);
			$('<p />', {  'class': 'myName', 'text': myself.userName } ).appendTo(aboveDiv);
			$('<p />', {  'class': 'myIntro', 'text': myself.profile } ).appendTo(aboveDiv);
			$('<button />', {  'id': 'editBtn', 'text': 'edit' } ).appendTo(aboveDiv);
			$(aboveDiv).appendTo(mypageAbove);
		    });
		$("#mypage").dialog("open");
	}, () => {
		console.error('Error: ajax connection failed.');
	}
	);
};
