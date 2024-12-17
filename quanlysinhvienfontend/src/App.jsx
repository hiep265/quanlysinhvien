import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import KetQuaHP from "./components/ketquahp/KetQuaHP";
import NapTien from "./components/naptien/NapTien";
import QLTaiChinh from "./components/qltaichinh/QLTaiChinh";
import TrangChu from "./components/TrangChu";
import Header from "./components/header/Header";


function App() {
  

  return (
    <Router>
      <Header/>
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
