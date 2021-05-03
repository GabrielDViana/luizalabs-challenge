import './App.css';
import Routes from './routes';
import "bootstrap/dist/css/bootstrap.min.css";
import { MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';

function App() {
	return (
		<MuiPickersUtilsProvider utils={DateFnsUtils}>
			<Routes />
		</MuiPickersUtilsProvider>
	);
}


export default App;
