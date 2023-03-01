
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import AddEmployee from './employee/AddEmployee';
import EditEmployee from './employee/EditEmployee';
import Login from './pages/Login';

import Signup from './pages/Signup';



  function App() {
    
  
  return (

    <div>
    <div className="App">
   <Router>
   <Navbar />
   <Routes>
    <Route exact path='/home' element={<Home />}/>
    <Route exact path='/addemployee' element={<AddEmployee/>}/>
    <Route exact path='/editemployee/:id' element={<EditEmployee/>}/>
    <Route exact path="/login" element={<Login/>} />
    <Route exact path='/signup' element={<Signup />} />
   </Routes>
   
   </Router>
    </div>
    </div>
  );
}

export default App;
