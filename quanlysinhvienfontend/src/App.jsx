import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import KetQuaHP from "./components/KetQuaHP";
import NapTien from "./components/NapTien";
import QLTaiChinh from "./components/QLTaiChinh";
import TrangChu from "./components/TrangChu";
import Header from "./components/Header";
import './App.css'

function App() {
  

  return (
    <Router>
      <Header className="header"/>
      <div >
        <Routes>
          <Route path="/" element={<TrangChu/>}/>
          <Route path="/KetQuaHP" element={<KetQuaHP/>}/>
          <Route path="/NapTien" element={<NapTien/>}/>
          <Route path="/QLTaiChinh" element={<QLTaiChinh/>}/>
        </Routes>
      </div>
      
    </Router>
  )
}

export default App
