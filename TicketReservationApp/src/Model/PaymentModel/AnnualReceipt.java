package Model.PaymentModel;
import Model.PaymentModel.Receipt;
import Model.UserModel.RegisteredUser;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class AnnualReceipt extends Receipt {

	public AnnualReceipt() {
		super.setReceiptType("Annual Dues");
	}

	public AnnualReceipt(ResultSet rs) throws SQLException { //to complete, how is annual payment receipt stored in db? linked to userName?
		super(rs);
	}

	public AnnualReceipt(JSONObject jsonObj) throws JSONException, ParseException {
		super(jsonObj);
	}


}
