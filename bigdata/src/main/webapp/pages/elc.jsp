<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="bean.Elcbean"%>
<%@page import="java.util.List"%>
<%@page import="dao.Elcdao"%>
<%
request.setCharacterEncoding("utf-8"); 
response.setCharacterEncoding("utf-8");
Elcdao dao = new Elcdao();
List<Elcbean> list = dao.getTables();
%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>电力分析</title>

    <!-- Bootstrap Core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


</head>

<body>


    
    <div id="wrapper">

        <!-- Navigation -->
       

        <!-- /#page-wrapper -->
        <div id="page-wrapper">
            <div class="row">
                   
            </div>
            <!-- /.row -->
            <div class="row">
            <div id="container" style="height: 550%"></div> <!-- 折线图 -->
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           用户缴费记录
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">  
                            <table style="width:100%;" class="table table-striped table-bordered table-hover" id="mytable">
                                <thead>
                                    <tr>
                                        <th>用户id</th>
                                        <th>缴费日期</th>
                                        <th>缴费金额</th>

                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                for(int i=0;i<list.size();i++){
                                    out.print("<tr>"
                                    		+"<td>"+list.get(i).getId()+"</td>"
                                    		+"<td>"+list.get(i).getDate()+"</td>"    
                                    		+"<td>"+list.get(i).getMoney()+"</td>"    
                                            +"</tr>"); 
                                    }
                                %>
                                </tbody>
                            </table>
                            
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /#page-wrapper -->
           
        

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../vendor/metisMenu/metisMenu.min.js"></script>

    <!-- DataTables JavaScript -->
    <script src="../vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="../vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
    <script src="../vendor/datatables-responsive/dataTables.responsive.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../dist/js/sb-admin-2.js"></script>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>
        
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5.3.1/dist/echarts.min.js"></script>

        <script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};

var option;

option = {
  title: {
    
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
	  data:['1000000019','1000000036','1000000041','1000000046','1000000048','1000000066','1000000078','1000000097','1000000098','1000000099',]
	  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data:['2018-03-01','2018-03-07','2018-03-11','2018-05-03','2018-07-02','2018-08-05','2018-09-05','2018-09-07','2018-09-11','2018-09-20','2018-11-07','2018-11-12','2018-11-14','2019-01-04','2019-03-04','2019-03-08','2019-03-12']
  },
  yAxis: {
    type: 'value'
  },
  series: [
        {
          name: '1000000019',
          type: 'line',
          data: [['2018-03-07',136],['2018-05-03',97],['2018-07-02',126],['2018-09-07',137],['2018-11-12',118],['2019-03-08',115]]
        },
        {
            name: '1000000036',
            type: 'line',
            data: [['2018-05-03',71],['2018-07-02',117],['2018-09-11',100],['2018-11-14',143],['2019-01-04',87],['2019-03-12',127]]
        },
        {
            name: '1000000041',
            type: 'line',
            data: [['2018-03-11',122],['2018-05-03',141],['2018-07-02',123],['2018-09-20',93],['2018-11-14',145],['2019-01-04',59]]      
      },
      {
            name: '1000000046',
            type: 'line',
            data: [['2018-03-11',129],['2018-05-03',104],['2018-07-02',112],['2018-11-07',141],['2019-01-04',119],['2019-03-12',106]]     
        },
       {
           name: '1000000048',
           type: 'line',
           data: [['2018-03-01',139],['2018-05-03',115],['2018-07-02',139],['2018-11-07',89],['2019-01-04',77],['2019-03-04',119]]
        },
        {
      	  name: '1000000066',
      	  type: 'line',
      	  data: [['2018-03-01',141],['2018-05-03',126],['2018-07-02',117],['2018-09-05',96],['2018-11-07',83],['2019-03-04',136]]
        }, 
     	  {
        	name: '1000000078',
        	type: 'line',
        	data: [['2018-03-01',127],['2018-09-05',124],['2018-11-07',79],['2019-01-04',130],['2019-03-04',132]]
     	  },
     	  {
          	name: '1000000097',
          	type: 'line',
          	data: [['2018-03-01',94],['2018-07-02',106],['2018-08-05',106],['2018-11-07',98],['2019-01-04',115],['2019-03-04',130]]
        },
        {
       	   name: '1000000098',
       	   type: 'line',
       	   data: [['2018-03-01',132],['2018-05-03',99],['2018-07-02',91],['2018-09-05',164],['2018-11-07',144],['2019-01-04',105]]
       	},
       	{
       	     name: '1000000099',
       	     type: 'line',
       	     data: [['2018-03-01',95],['2018-05-03',169],['2018-07-02',144],['2018-09-05',156],['2018-11-07',174],['2019-03-04',115]]
        } 
  ]
  
};


if (option && typeof option === 'object') {
    myChart.setOption(option);
}
myChart.on('click', function (param) {//点击事件
	var seriesIndex = param.seriesIndex;
    var dataIndex = param.dataIndex;
    var seriesName = param.seriesName;		//seriesName
    var name = param.name;					//name
    var data = param.data;
    var value = param.value;
    console.dir(param);
    var str = "seriesIndex:"+seriesIndex+"\n"+"dataIndex:"+dataIndex+"\n"+"seriesName:"+seriesName+"\n"+
             "name:"+name+"\n"+"data:"+data+"\n"+"value:"+value;
   	search(seriesName,name);
   	
    
});

function search(seriesName,name) {
	  var tb=document.getElementById("mytable");
	  var rows = tb.rows;

	  for (var i = 1; i < rows.length; i++) {
	      var cells = rows[i].cells;
	      for (var j = 0; j < 2; j=j+2) {
	          var value1 = cells[j].innerHTML;
	          var value2 = cells[j+1].innerHTML;
	          
	          if(value1 == seriesName){
	        	  tb.rows[i].style.display = "";
	        	  tb.rows[i].scrollIntoView();
	        	  if(value2==name){
	        		  tb.rows[i].style.backgroundColor="red";
	        	  }else{
	        		  tb.rows[i].style.backgroundColor="";
	        	  }
	          }else{
	        	  tb.rows[i].style.display = "none";
	        	  
	          }
	      }
	  }
}

        </script>

</body>

</html>
