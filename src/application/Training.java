package application;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Training {

	private final IntegerProperty trainingID;
	private final Date trainingDate;
	private final IntegerProperty trainingDuration;

	public Training(Integer trainingID, Date trainingDate, Integer trainingDuration) {
		this.trainingID = new SimpleIntegerProperty(trainingID);
		this.trainingDate = trainingDate;
		this.trainingDuration = new SimpleIntegerProperty(trainingDuration);
	}
	
	public String getTreningID() {
		return treningID.get();
	}

	public void setTreningID(String treningID) {
		this.treningID.set(treningID);
	}
	
	public StringProperty treningIDProperty() {
		return treningID;
	}
	
	public String getTreningCode() {
		return treningCode.get();
	}

	public void setTreningCode(String treningCode) {
		this.treningCode.set(treningCode);
	}
	
	public StringProperty treningCodeProperty() {
		return treningCode;
	}

	public String getTreningName() {
		return treningName.get();
	}

	public void setTreningName(String treningName) {
		this.treningName.set(treningName);
	}
	
	public StringProperty treningNameProperty() {
		return treningName;
	}
}