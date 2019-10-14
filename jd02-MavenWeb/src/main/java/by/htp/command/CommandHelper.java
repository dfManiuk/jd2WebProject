package by.htp.command;

import java.util.HashMap;
import java.util.Map;
import by.htp.command.impl.AddMedicationCommand;
import by.htp.command.impl.AddNewPatientCommand;
import by.htp.command.impl.AddPhotoCommand;
import by.htp.command.impl.AllDischangedPatientsCommand;
import by.htp.command.impl.AllDissmisedUsersCommand;
import by.htp.command.impl.AllMedicationCommand;
import by.htp.command.impl.AllPatientsCommand;
import by.htp.command.impl.BackUserPageCommand;
import by.htp.command.impl.DelMedicationCommand;
import by.htp.command.impl.EditMedicationCommand;
import by.htp.command.impl.FindPatientCommand;
import by.htp.command.impl.FindPatientFromUser;
import by.htp.command.impl.LoginationCommand;
import by.htp.command.impl.PatientDiagnosisCommand;
import by.htp.command.impl.PatientDischangeCommand;
import by.htp.command.impl.PatientFixingCommand;
import by.htp.command.impl.PatientMedicationCommand;
import by.htp.command.impl.PatientMedicationPeriosityCommand;
import by.htp.command.impl.PatientOutOffCommand;
import by.htp.command.impl.PatientRightOutCommand;
import by.htp.command.impl.PatientUnFixingCommand;
import by.htp.command.impl.ShowAllUsersCommand;
import by.htp.command.impl.UserChangerCommand;
import by.htp.command.impl.UserDismissalCommand;
import by.htp.command.impl.UserEditProfile;
import by.htp.command.impl.UserMedicalPeriodCommand;
import by.htp.command.impl.UserRegistration;

public class CommandHelper {

	private static final CommandHelper instance = new CommandHelper();
	
	private Map<CommandName, ICommand> commands = new HashMap<>();
	private Map<CommandName, FileCommand> commandsF = new HashMap<>();
	
	public CommandHelper() {
		commands.put(CommandName.LOGINATION, new LoginationCommand());
		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		commands.put(CommandName.REGISTRATION, new UserRegistration());	
		commands.put(CommandName.USER_EDIT_PROFILE, new UserEditProfile());
		commands.put(CommandName.ADD_NEW_PATIENT, new AddNewPatientCommand());
		commands.put(CommandName.FIND_PATIENT, new FindPatientCommand());
		commands.put(CommandName.FIXING, new PatientFixingCommand());
		commands.put(CommandName.MEDICATION, new PatientMedicationCommand());
		commands.put(CommandName.FIND_PATIENT_FROM_USER, new FindPatientFromUser());
		commands.put(CommandName.EDIT_MEDICATION, new EditMedicationCommand());
		commands.put(CommandName.DEL_MEDICATION, new DelMedicationCommand());
		commands.put(CommandName.ADD_MEDICATION, new AddMedicationCommand());
		commands.put(CommandName.ALL_MEDICATION, new AllMedicationCommand());
		commands.put(CommandName.UNFIXING, new PatientUnFixingCommand());
		commands.put(CommandName.LIVE, new PatientDischangeCommand());
		commands.put(CommandName.DIAGNOSIS, new PatientDiagnosisCommand());
		commands.put(CommandName.RIGHT_OUT, new PatientRightOutCommand());
		commands.put(CommandName.OUT_OFF, new PatientOutOffCommand());
		commands.put(CommandName.BACK_USER_PAGE, new BackUserPageCommand());
		commands.put(CommandName.USER_CHANGER, new UserChangerCommand());
		commands.put(CommandName.DISMISSAL, new UserDismissalCommand());
		commands.put(CommandName.ADD_MEDICATION_PERIOD, new UserMedicalPeriodCommand());
		commands.put(CommandName.MEDICATION_PEREOSITY, new PatientMedicationPeriosityCommand());
		commands.put(CommandName.SHOW_ALL_USERS, new ShowAllUsersCommand());
		commands.put(CommandName.USER_DISSMISAL_LIST, new AllDissmisedUsersCommand());
		commands.put(CommandName.SHOW_ALL_PATIENTS, new AllPatientsCommand());
		commands.put(CommandName.PATIENT_DISCHANGED_LIST, new AllDischangedPatientsCommand());
		
		commandsF.put(CommandName.ADD_PHOTO, new AddPhotoCommand());
		
	}                         

	
	public ICommand getCommand(String commandName) {
		
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		
		ICommand command;
		if (null !=name) {
			command = commands.get(name);
		} else {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}
		return command;
	}
	public FileCommand getCommandFile(String commandName) {
		
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		
		FileCommand commandFileCommand = null;
		if (null !=name) {
			commandFileCommand = commandsF.get(name);
		}
		return commandFileCommand;
	}
	
	public static CommandHelper getInstance() {
		return instance;
	}
}
