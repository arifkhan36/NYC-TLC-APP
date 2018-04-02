import React, { Component } from 'react';

class Search extends Component {

   


    render() {
        return (
        <div className="Box">
          <form
            className="search"
            onSubmit={this.props.submitForm}
          >
          <input
            type="text"
            value={this.props.inputValue}
            name="content"
            placeholder="Search here"
            onChange={this.props.handleInputValue}
          />

          </form>
        </div>
        );
      }
    }

 
export default Search;