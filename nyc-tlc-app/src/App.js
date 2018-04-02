import React, { Component } from 'react';
import DriverList from './components/DriverList';
import Search from './components/Search';
import Navbar from './components/Navbar';
import RenewalList from './components/RenewalList';
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


  render () {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    return (
      <div>
        <section className="list">
        <Navbar />
        <Search 
        submitForm = {this.submitForm} 
        handleInputValue = {this.handleInputValue}
        inputValue = {this.inputValue}
        
        
        />
       
        <DriverList driver={this.state.driver}/>
        
        </section>
        <div>
            <button
                onClick={ this.handleCreateSubmit}>
                Add to Renewallist 
            </button>
        </div>

        <h3>My Renewal Driver List</h3>
        {
            this.state.driverTable.map((driver, index) => {
                return(
                    <RenewalList
                      {...driver}
                      key={index}
                      index={index}
                    />

                )
            })
        }
        
      </div>
   
    )
  }
}

export default App;
