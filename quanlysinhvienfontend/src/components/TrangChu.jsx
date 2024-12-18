

import { Link, useNavigate } from 'react-router-dom';

function TrangChu() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated'); 
    localStorage.removeItem('userID');
    navigate('/'); 
  };

  return (
    <div>
      <h2>Trang Chủ</h2>
      <ul>
      <li>
          <Link to="/HocPhan">Xem Các Học Phần</Link>
        </li>
        <li>
          <Link to="/KetQuaHP">Xem Kết Quả Học Phần</Link>
        </li>
        <li>
          <Link to="/NapTien">Nạp Tiền</Link>
        </li>
        <li>
          <Link to="/QLTaiChinh">Thông Tin Tài Chính</Link>
        </li>


      </ul>
      <button onClick={handleLogout}>Đăng xuất</button>
    </div>
  );
}

export default TrangChu;