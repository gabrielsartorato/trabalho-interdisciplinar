import React from 'react';

import Rotas from './routes'

import 'toastr/build/toastr.min.js'

import 'bootswatch/dist/flatly/bootstrap.css'
import '../custom.css'
import 'toastr/build/toastr.css'

import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

import SideBar from '../components/sideBar';

class App extends React.Component {

  render() {

    return (
      <>
        <SideBar />
        <div className="container">
          <Rotas />
        </div>
      </>
    );

  }

}
export default App;
