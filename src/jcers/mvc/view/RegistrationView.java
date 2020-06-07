package jcers.mvc.view;

/**
 * File:		RegistrationView.java
 * Created:		Apr/08/2016
 * Author:		Piotr Kapela https://github.com/pkapela
 * Description:		
 * 		
 */

import jcers.mvc.model.CourseModel;
import jcers.mvc.model.DBase;
import jcers.mvc.model.PaymentModel;
import java.util.Date;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class RegistrationView extends BorderPane
{
	// Constant(s) [Private Access]
	private final double COL_WIDTH = 185;
	private final double TABLE_WIDTH = 743;
	private final double TABLE_HEIGHT = 300;

	// Label(s) [Default Access]
	Label titleLbl = new Label("Registered Classes");
	Label paymentLbl = new Label("Payments");
	Label totalTuitionLbl = new Label("Total Tuition:");
	Label totalPaymentLbl = new Label("Total Payment:");
	Label balanceLbl = new Label("Balance:");
	Label registrationLbl = new Label("Registration Fee:");
	Label otherFeesLbl = new Label("Other Fees");

	// TextField(s) [Default Access]
	TextField totalTuitionFld = new TextField();
	TextField totalPaymentFld = new TextField();
	TextField balanceFld = new TextField();
	TextField registrationFld = new TextField();
	TextField otherFeesFld = new TextField();

	// Button(s) [Default Access]
	Button closeBtn = new Button("Close");

	// Table Column(s) [Default Access]
	TableColumn<CourseModel, String> idCol = new TableColumn<CourseModel, String>("CourseID");
	TableColumn<CourseModel, String> courseNameCol = new TableColumn<CourseModel, String>("Course Name");
	TableColumn<CourseModel, Integer> courseTuitionCol = new TableColumn<CourseModel, Integer>("Tuition");
	TableColumn<CourseModel, Date> regDateCol = new TableColumn<CourseModel, Date>("Registration Date");

	TableColumn<PaymentModel, String> tuitionPaidCol = new TableColumn<PaymentModel, String>("Paid");
	TableColumn<PaymentModel, String> paymentMethodCol = new TableColumn<PaymentModel, String>("Payment Method");
	TableColumn<PaymentModel, String> payerNameCol = new TableColumn<PaymentModel, String>("Payer Name");
	TableColumn<PaymentModel, String> cardDigitsCol = new TableColumn<PaymentModel, String>("Last 4 Digits");

	// TableView [Default Access]
	TableView<CourseModel> courseTable = new TableView<CourseModel>();
	TableView<PaymentModel> paymentTable = new TableView<PaymentModel>();

	// ImageView(s) [Private Access]
	private ImageView removeImgView = new ImageView(
			new Image("/jcers/images/cancel_icon.png"));
	private ImageView addImgView = new ImageView(
			new Image("/jcers/images/add_icon.png"));

	// ContextMenu [Default Access]
	ContextMenu regContextMenu = new ContextMenu();
	MenuItem regAddCtx = new MenuItem("Add Course");
	MenuItem regRemoveCtx = new MenuItem("Remove Course");

	ContextMenu payContexMenu = new ContextMenu();
	MenuItem payAddCtx = new MenuItem("Add Payment");
	MenuItem payRemoveCtx = new MenuItem("Remove Payment");

	public RegistrationView()
	{
		layoutForm();
		createContextMenu();
	}

	// Method(s) [Private Access]
	private void layoutForm()
	{
		VBox vbox = addVBox();
		VBox tables = addTables();
		VBox summarySection = addSummarySection();

		this.setLeft(vbox);
		this.setRight(summarySection);
		this.setCenter(tables);

		return;
	}

	@SuppressWarnings("unchecked")
	private VBox addTables()
	{
		VBox tablePane = new VBox();
		tablePane.setSpacing(5);
		tablePane.setPadding(new Insets(10, 0, 0, 10));

		// Setting registered courses table
		courseTable.setEditable(false);
		courseTable.setFixedCellSize(25);
		courseTable.setStyle("-fx-focus-color: transparent;");
		courseTable.setMaxHeight(TABLE_HEIGHT);
		courseTable.setMaxWidth(TABLE_WIDTH);

		idCol.setCellValueFactory(new PropertyValueFactory<CourseModel, String>("courseID"));
		courseNameCol.setCellValueFactory(new PropertyValueFactory<CourseModel, String>("courseName"));
		courseTuitionCol.setCellValueFactory(new PropertyValueFactory<CourseModel, Integer>("courseTuition"));
		regDateCol.setCellValueFactory(new PropertyValueFactory<CourseModel, Date>("registrationDate"));

		idCol.setPrefWidth(COL_WIDTH);
		courseNameCol.setPrefWidth(COL_WIDTH);
		courseTuitionCol.setPrefWidth(COL_WIDTH);
		regDateCol.setPrefWidth(COL_WIDTH);

		courseTable.getColumns().addAll(idCol, courseNameCol, courseTuitionCol, regDateCol);
		courseTable.setItems(DBase.loadRegData());
		titleLbl.setFont(new Font("Arial", 20));

		// Setting payment table
		paymentTable.setEditable(false);
		paymentTable.setFixedCellSize(25);
		paymentTable.setStyle("-fx-focus-color: transparent;");
		paymentTable.setMaxHeight(TABLE_HEIGHT - 100);
		paymentTable.setMaxWidth(TABLE_WIDTH);

		tuitionPaidCol.setCellValueFactory(new PropertyValueFactory<PaymentModel, String>("tuitionPaid"));
		paymentMethodCol.setCellValueFactory(new PropertyValueFactory<PaymentModel, String>("paymentMethod"));
		payerNameCol.setCellValueFactory(new PropertyValueFactory<PaymentModel, String>("payerName"));
		cardDigitsCol.setCellValueFactory(new PropertyValueFactory<PaymentModel, String>("cardDigits"));

		tuitionPaidCol.setPrefWidth(COL_WIDTH);
		paymentMethodCol.setPrefWidth(COL_WIDTH);
		payerNameCol.setPrefWidth(COL_WIDTH);
		cardDigitsCol.setPrefWidth(COL_WIDTH);

		paymentTable.getColumns().addAll(tuitionPaidCol, paymentMethodCol, payerNameCol, cardDigitsCol);
		paymentTable.setItems(DBase.loadPayData());
		paymentLbl.setFont(new Font("Arial", 20));

		tablePane.getChildren().add(titleLbl);
		tablePane.getChildren().add(courseTable);

		tablePane.getChildren().add(paymentLbl);
		tablePane.getChildren().add(paymentTable);

		return tablePane;
	}

	private VBox addVBox()
	{
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);
		vbox.setStyle("-fx-background-color: #999966;");

		closeBtn.setPrefSize(100, 20);

		vbox.setAlignment(Pos.BOTTOM_CENTER);
		vbox.getChildren().addAll(closeBtn);

		return vbox;
	}

	private VBox addSummarySection()
	{
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(40, 40, 40, 50));
		gp.setHgap(13);
		gp.setVgap(11);

		GridPane.setHalignment(registrationLbl, HPos.RIGHT);
		gp.add(registrationLbl, 0, 0);
		gp.add(registrationFld, 1, 0);

		GridPane.setHalignment(otherFeesLbl, HPos.RIGHT);
		gp.add(otherFeesLbl, 0, 1);
		gp.add(otherFeesFld, 1, 1);

		GridPane.setHalignment(totalTuitionLbl, HPos.RIGHT);
		gp.add(totalTuitionLbl, 0, 2);
		gp.add(totalTuitionFld, 1, 2);

		GridPane.setHalignment(totalPaymentLbl, HPos.RIGHT);
		gp.add(totalPaymentLbl, 0, 3);
		gp.add(totalPaymentFld, 1, 3);

		GridPane.setHalignment(balanceLbl, HPos.RIGHT);
		gp.add(balanceLbl, 0, 4);
		gp.add(balanceFld, 1, 4);

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);
		vbox.setStyle("-fx-font-size: 13px;" + "-fx-focus-color: transparent;" + "-fx-background-color: #e6e6e6;");

		vbox.setAlignment(Pos.BOTTOM_CENTER);
		vbox.getChildren().addAll(gp);

		return vbox;
	}

	private void createContextMenu()
	{
		addImgView.setFitHeight(16);
		addImgView.setFitWidth(16);
		removeImgView.setFitHeight(16);
		removeImgView.setFitWidth(16);
		
		regAddCtx.setGraphic(addImgView);
		regRemoveCtx.setGraphic(removeImgView);
		regContextMenu.getItems().addAll(regAddCtx, regRemoveCtx);
		
		payAddCtx.setGraphic(addImgView);
		payRemoveCtx.setGraphic(removeImgView);
		payContexMenu.getItems().addAll(payAddCtx, payRemoveCtx);

		return;
	}

} // End of RegistrationView Class
