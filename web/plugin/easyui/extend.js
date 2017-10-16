jQuery.extend({
	//��form�?�еĲ������л�Ϊhash
	serializeObject: function(form) {
		var o={};
		$.each(form.serializeArray(),function(index){
			if(o[this['name']]){
				o[this[['name']]]=o[this[['name']]]+","+this['value'];
			}else{
				o[this[['name']]]=this['value'];
			}
		});
		return o;
	},
	
	//根据参数百分比来获取整个屏幕的宽高百分比
	//如getHeight(0.2)就是获取屏幕高度的20%
	getHeight:function (percent){
		return parent.document.body.clientHeight *percent;
	},
	getWidth:function (percent){
		return parent.document.body.clientWidth *percent;
	},
	//获取指定tab页面中的iframe对象，如果没有指定就默认为当前选中的tab页
	getIFrame:function(index){
		if(index == null || index == ""){
			var tab = parent.$('#maintabs').tabs('getSelected');
			index = parent.$('#maintabs').tabs('getTabIndex',tab);
		}
		return parent.document.getElementsByTagName("iframe")[index].contentWindow;
	},
	
	/**
	 * ���ܣ�easyui��ݱ��̬��ҳ
	 * ����
	 * 	div���������div��Ψһ��ʾ��һ����ID��
	 * 	table������Ψһ��ʾ��һ����ID��
	 * 	limit����̬��ҳ������
	 */
	dynamicPaginate: function(div, table, limit) {
		var total = $(table).datagrid('getData').total;
		if (total <= 10 && $(table).datagrid('getPager').data('pagination') != undefined) {
			//������С�ڵ���limit�����ط�ҳ��
			$(div).find(".pagination").hide();
		} else if (total > 10) {
			//���������limit����ʾ��ҳ��
			$(div).find(".pagination").show();
		}
	},
	
	/**
	 * ���ܣ������������е�Ԫ��ȡ���������
	 */
	ObjectArray2AttrArray: function(ObjectArray, attr) {
		var AttrArray = [];
		$.each(ObjectArray, function(n, object) {
			AttrArray[n] = object[attr];
		})
		return AttrArray;
	},
	
	/**
	 * ���ܣ���ȡurl
	 */
	getUrl: function() {
		var href = window.location.pathname;
		//����Ŀ��ƽ�ȥ
		href = href.replace(href.match(/^\/[A-Za-z]+\//)[0], "");
		//�����ڶ��ţ��������Ժ�����ݽ�ȥ
		var index = href.indexOf(";");
		if (index == -1) {
			return href;
		} else {
			return href.substring(0, index);
		}
	},
	
	/**
	 * ���ַ�ת��Ϊʱ��
	 */
	string2Time: function(timeString) {
		var s = timeString.split(" ");
		var s1 = s[0].split("-");
		var s2 = s[1].split(":");
		return d = new Date(s1[0], s1[1] - 1, s1[2], s2[0], s2[1], s2[2]);
	},
	
	/**
	 * ���ܣ�����select��ѡ��ѡ����û��ѡ����ѡ��
	 * @param: selDomId ��ѡ��id
	 * @param: optValue ѡ��ֵ
	 */
	setSelOpt: function(selDomId, optValue) {
		var option = $("#" + selDomId + " option[value=" + optValue + "]");
		if (option.val() == undefined) {
			//ѡ����ڣ����ѡ��
			$("#" + selDomId).append('<option value=' + optValue + ' selected="selected"></option>');
		}
	},
	
	/**
	 * ���ܣ�����select��ѡ��ѡ��������ѡ�ɾ��ѡ��
	 * @param: selDomId ��ѡ��id
	 * @param: optValue ѡ��ֵ
	 */
	setUnSelOpt: function(selDomId, optValue) {
		var option = $("#" + selDomId + " option[value=" + optValue + "]");
		if (option.val() != undefined) {
			//ѡ����ڣ�ɾ��ѡ��
			option.remove();
		}
	},
	
	/**
	 * ���ܣ����ids��Ӧ��ѡ��
	 * @param: selDomId ��ѡ��id
	 * @param: optValues ѡ��ֵ����
	 */
	setSelOptAllSelected: function(selDomId, optValues) {
		$.each(optValues, function(n, v) {
			var option = $("#" + selDomId + " option[value=" + v + "]");
			if (option.val() == undefined) {
				//ѡ����ڣ����ѡ��
				$("#" + selDomId).append('<option value=' + v + ' selected="selected"></option>');
			}
		});
	},
	
	/**
	 * ���ܣ�ɾ��ids��Ӧ��ѡ��
	 * @param: selDomId ��ѡ��id
	 * @param: optValues ѡ��ֵ����
	 */
	setSelOptAllUnSelected: function(selDomId, optValues) {
		$.each(optValues, function(n, v) {
			var option = $("#" + selDomId + " option[value=" + v + "]");
			if (option.val() != undefined) {
				//ѡ����ڣ�ɾ��ѡ��
				option.remove();
			}
		});
	},
	
	/**
	 * ���ܣ�table�����
	 * @param: tbodyId ���body��id
	 * @param: trId ��id
	 * @param: tdArr �����
	 */
	addTBodyList: function(tbodyId, trId, tdArr) {
		var tr = $("#" + tbodyId).find("#" + trId)[0];
		if (tr == undefined) {
			//�в����ڣ������
			var html = "<tr id=" + trId + ">";
			$.each(tdArr, function(n ,v) {
				html += "<td>" + v + "</td>";
			});
			html += "</tr>";
			$("#" + tbodyId).append(html);
		}
	},
	
	/**
	 * ���ܣ�tableɾ����
	 * @param: tbodyId ���body��id
	 * @param: trId ��id
	 */
	delTBodyList: function(tbodyId, trId) {
		var tr = $("#" + tbodyId).find("#" + trId)[0];
		if (tr != undefined) {
			//�д��ڣ�ɾ����
			$(tr).remove();
		}
	},
	
	/**
	 * ���ܣ�table���������
	 * @param: listId dataTable�б�id
	 * @param: selId ��ѡ��id
	 * @param: tbodyId ���body��id
	 * @param: trId ��id
	 * @param: ids �������
	 * @param: names �������
	 */
	addAllTBodyList: function(listId, selId, tbodyId, trId, ids, names) {
		if (ids.length != names.length) {
			return;
		}
		$.each(ids, function(n, v) {
			var tr = $("#" + tbodyId).find("#" + trId + "_" + v)[0];
			if (tr == undefined) {
				//�в����ڣ������
				var html = '<tr id=' + trId + '_' + v + '>';
				html += '<td>' + ids[n] + '</td>';
				html += '<td>' + names[n] + '</td>';
				html += '<td><a href="javascript:void(0)" onclick=uncheck("' + listId + '","' + selId + '",' + ids[n] + ',' + 'this' + ')>ȡ��</a>'
				html += '</tr>';
				$("#" + tbodyId).append(html);
			}
		});
	},
	
	/**
	 * ���ܣ�tableɾ�������
	 * @param: tbodyId ���body��id
	 * @param: trId ��id
	 * @param: ids �������
	 */
	delAllTBodyList: function(tbodyId, trId, ids) {
		$.each(ids, function(n, v) {
			var tr = $("#" + tbodyId).find("#" + trId + "_" + v)[0];
			if (tr != undefined) {
				//�д��ڣ�ɾ����
				$(tr).remove();
			}
		});
	},
	
	/**
	 * ���ܣ�������ת��Ϊ���ģ����磬1:һ,10:ʮ��
	 */
	formatIntChinese: function(strInt) {
		var arBigDigit = ["��", "һ", "��", "��", "��", "��", "��", "��", "��", "��"]
		var arUnitBase = ["ǧ", "��", "ʮ", ""]
		var arUnitAdv = ["", "��", "��"]
		
		var iIndex = 0, iIndexAdv = 0, iDigit = 0, iCount = 0;
		var strRet = "";
		
		// ���㵽ǧ��
		iCount = strInt.length / 4;
		if(iCount * 4 < strInt.length)
		{
			iCount ++;
		}
		if(iCount > 3)
		{
			iCount = 3;
		}
		
		for (iIndexAdv = 0; iIndexAdv < iCount; iIndexAdv ++)
		{
			var iEnd = strInt.length - iIndexAdv * 4;
			var iStart = iEnd - 4;
			var iUnitOff = 0;
			var iZeroFlag = 0;
			
			if(iStart < 0)
			{
				iStart = 0;
			}

			var strTmp = strInt.substring(iStart, iEnd);
			var strUnitAdv = arUnitAdv[iIndexAdv];
			var __ret01 = "";

			if (eval(strTmp) == 0)
			{
				continue;
			}
			
			iUnitOff = 4 - strTmp.length;
			for (iIndex = strTmp.length - 1; iIndex > -1; iIndex --)
			{
				if((iDigit = eval(strTmp.substring(iIndex, iIndex + 1))) > 0)
				{
					__ret01 = arBigDigit[iDigit] + arUnitBase[iUnitOff + iIndex] + __ret01;
					iZeroFlag = 0;
	            }
				else if (iZeroFlag == 0)
				{
				    if (iIndex != 3)
				    {
				        __ret01 = arBigDigit[0] + __ret01;
				    }
			        iZeroFlag = 1;
				}
			}
			
			strRet = __ret01 + strUnitAdv + strRet;
		}

	    return strRet;
	},
	
	formatTwoDecimal: function(num) {
		if (!isNaN(num)) {
			var dot = num.indexOf('.');
			if (dot != -1) {
				var dotCnt = num.substring(dot+1, num.length);
				if (dotCnt.length > 2) {
					var temp = Number(num);
					return temp.toFixed(2);
				} else {
					return num;
				}
			} else {
				return num;
			}
		} else {
			return 0;
		}
	}
})