<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="onlineStore.jsp"></jsp:include>

<html>
<head>
<META content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Discounts</title>
 <SCRIPT type="text/javascript">
		    var access = false;
		    
		    function save(){
		     
		          $("form").submit();
 </SCRIPT>
 <script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<link type="text/css" rel="stylesheet" href="css/products.css">
</head>

<body>
<form method="post" action="servlet/addDiscount">
<div class="container">
<div class="header"><table>
  <tr>
    <td width="903" height="52"><a href="#">Discounts</a>/Create discount</td>
    <td width="278"><input type="submit"  width="100px" height="50px" name="cancel" id="cancel" value="Cancel" />
                       <input type="submit" name="save" id="save" value="Save" onClick="save()"/>
    </td>
  </tr>
</table></div>


    <!-- end .header -->
  <div class="sidebar1">
  <table>
    <tr>
      <td><h3>Date range</h3></td>
    </tr>
     <tr> <td>start date</td></tr>
    <tr> <td><input id="start_date"  name="start_date" type="text" class="Wdate" onClick="WdatePicker();"/></td></tr>
      <tr> <td>end date</td></tr>
      <tr> <td><input id="end_date" name="end_date" type="text" onClick="WdatePicker()"/></td></tr>
      <tr>
      <td> <input type="checkbox" name="statue" value="0" id="end_date" />
        No end date</td></tr>
  </table>
</div>
<!--RIGHT END-->
  <div class="content">
    <h3>Discount code</h3>
      <input type="text" style="width:300px;height:30px" name="discount_code" id="discount_code" />
 <p> Customers will enter this discount code at checkout.</p>
 <br><p></p>
  <img src="image/line.png"  />
    <h3>Conditions</h3>
      <table style="width: 933px; "><tr>
      <td width="120px"> <select name="discount_unit"  id="discount_unit" style="height:30px;width:100px">
           <option>$SGD</option>
           <option selected="selected">%Discount</option>
           <option>Free Shipping</option>
         </select></td>
         <td width="20px">take</td>
         <td width="80px"><input type="text" style="width:80px;height:30px" name="discount_ratio" id="discount_ratio" /></td>
         <td width="55px">off for</td>
         <td width="80px"><select name="discount_kind"  id="discount_kind" style="height:30px">
           <option>all orders</option>
           <option selected="selected">orders over</option>
           <option>collection</option>
            <option>product</option>
             <option>product variant</option>
              <option>customer in group</option>
         </select></td>
         <td style="width: 520px; height: 34px; "></td>
      </tr> 
        <tr><td colspan="6">
      <img src="image/line.png"  />
      </td></tr></table>
   
   
   
    <table>
      <tr>
        <td width="444"><h3>Usage limits</h3></td>
        <td width="444">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">Total available</td>

      </tr>
      <tr>
        <td> <p>
    <input type="radio" name="discount_limit" id="radio" value="radio" style="height:30px" />
    <label for="radio"></label> 
    Unlimited
</p>
  <p>
    <input type="radio" name="radio2" id="radio2" value="radio2" style="height:30px" />
    
    <label for="radio2"></label>
  Limited number of uses</p></td>
        <td> </td>
      </tr>
    <tr><td colspan="2"> <img src="image/line.png"  /></td></tr>
      <tr>
           
        <td colspan="2"> Limit per customer </td>
      </tr>
      <tr>
        <td colspan="2"> <input type="checkbox" name="discount_limit_customer" value="0" style="height:30px" id="discount_limit_customer" />
        1 use only (tracked by customer's email)</td>
      </tr>
    </table>
     
  </div>
  </div>
  </form>

</body>
</html>
