import React, { Component } from 'react';
import DriverList from './components/DriverList';
import Search from './components/Search';
import Navbar from './components/Navbar';
import RenewalList from './components/RenewalList';
import Authen from './components/Authen';
import { Image } from 'semantic-ui-react';
import assets from './assets/tlc-taxi-image.jpg';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
// import Login from './components/Login';
import {
  Button, Container, Header
} from 'semantic-ui-react';
import './App.css';
import axios from 'axios';


const API = 'https://data.cityofnewyork.us/resource/pm46-7vyh.json'
class App extends Component {
  constructor (props) {
    super(props);

    this.state = {
       driverList: [],
       driver: {},
       inputValue: "",
       driverTable: []
    };

    this.getDriver = this.getDriver.bind(this)
    this.handleInputValue = this.handleInputValue.bind(this)
    this.submitForm = this.submitForm.bind(this);
    this.createRenewal = this.createRenewal.bind(this);
    this.handleCreateSubmit = this.handleCreateSubmit.bind(this);
  }
  

    handleInputValue(event) {
      this.setState({
        inputValue: event.target.value
      });
      console.log("inside handle input content ",event.target.value);
    }

    submitForm(event){
      event.preventDefault();
      console.log("event target is " + event.target.value);
      let value = this.state.inputValue;
      this.searchByName(value);
      this.searchByLicenseNumber(value);
  
      }
  getDriver(name){
    let finalURL = `${API}`;
    fetch(finalURL)
    .then((res) => res.json())
    .then((data) => {
      this.setState({
         driverList: data
             
      })
      console.log(this.state.driverList)
     

        })
    .catch((error) => console.log('issue with featching'))
  }

  componentDidMount(){
    this.getDriver(this.state.name);

    axios.get('/drivers/').then((res) => {
      this.setState({ driverTable: res.data});  
   })

  }


searchByName = (value) => {
  let listOfDrivers = this.state.driverList;
  let allDrivers = listOfDrivers.filter(lis => lis.name === value)

  if(allDrivers.length === 1) {
    console.log("list of " + allDrivers[0].name);
    let driverObj = {
      name: allDrivers[0].name,
      type: allDrivers[0].type,
      license_number: allDrivers[0].license_number,
      expiration_date: allDrivers[0].expiration_date,
      last_updated_date: allDrivers[0].expiration_date,
      last_updated_time: allDrivers[0].last_updated_time
    }
    this.setState({driver:driverObj})
  }else {
    console.log("not in there")
  }

}

searchByLicenseNumber = (value) => {
  let listOfDrivers = this.state.driverList;
  let allDrivers = listOfDrivers.filter(lis => lis.license_number === value)

  if(allDrivers.length === 1) {
    console.log(allDrivers);
    let driverObj = {
      name: allDrivers[0].name,
      type: allDrivers[0].type,
      license_number: allDrivers[0].license_number,
      expiration_date: allDrivers[0].expiration_date,
      last_updated_date: allDrivers[0].expiration_date,
      last_updated_time: allDrivers[0].last_updated_time
    }
    this.setState({driver:driverObj})
  }else {
    console.log("not found by NO")
  }

}

createRenewal = async (driver,index) => {
  try {
    const newListOfLib = await axios.post(`/drivers/`, driver)
    const updatedDriverList = [...this.state.driverTable];
    updatedDriverList.push(newListOfLib);

    this.setState({driverTable:updatedDriverList});
  }
  catch(error) {
    console.log('error in creating driverlist');
  }
}

handleCreateSubmit = (event) => {
  event.preventDefault();
  this.createRenewal(this.state.driver);
}

deleteRenewal = async (driverId, index) => {
  try {
    await axios.delete(`/drivers/${driverId}`)
    const updatedDriverList = [...this.state.driverTable]
    updatedDriverList.splice(index, 1)
    this.setState({driverTable: updatedDriverList})

  } catch (error) {
    console.log(`error in deleteing list ${driverId}`)
    console.log(error)

  }
}


  render () { 
    const style = {
      h1: {
        margin: '1em 50em',
      },
      h2: {
        margin: '4em 10em 12em',

      },
      h3: {
        marginRight: '12em',
        padding: '2em 10em',
        color:'red',
      },
      last: {
        marginBottom: '300px',
      },
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    return (
     
      <div>
         <Router>
          <Switch>
            <Route exact path="/login" component={Authen}/>
          </Switch>
        </Router>
        <section className="list">
        <Navbar />
        <Search 
        submitForm = {this.submitForm} 
        handleInputValue = {this.handleInputValue}
        inputValue = {this.inputValue}
        
        
        />
         <Image
         src={assets}
        //  as='a'
         size='massive' centered rounded
         opacity='0.5'
        />
        {/* <Login /> */}
        {/* <Authen /> */}
       
       
        <DriverList driver={this.state.driver}/>
        
        </section>
    
        <Header
                as='h3'
                style={style.h1}
                // textAlign='0 auto'
                />
            {/* <Container text> */}
            <Button primary
                onClick={ this.handleCreateSubmit}>
                AddList 
            </Button>
            {/* </Container> */}
        {
            this.state.driverTable.map((driver, index) => {
                return(
                    <RenewalList
                      {...driver}
                      key={index}
                      index={index}
                      deleteRenewal={this.deleteRenewal}
                    />

                )
            })
        }
        
      </div>
   
    )
  }
}

export default App;
