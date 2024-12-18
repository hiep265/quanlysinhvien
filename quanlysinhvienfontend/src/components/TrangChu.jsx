import { Link, useNavigate } from 'react-router-dom';

function TrangChu() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated'); 
    localStorage.removeItem('userID');
    localStorage.removeItem('username');
    navigate('/'); 
  };

  return (
    <div className="container mt-5">
      <div className="card shadow-lg p-4">
        <h2 className="text-center mb-4">Trang Chủ</h2>
        <ul className="list-group list-group-flush mb-4">
          <li className="list-group-item">
            <Link to="/HocPhan" className="btn btn-info btn-sm d-block mx-auto w-50 mb-2 fw-bold text-dark">Xem Các Học Phần</Link>
          </li>
          <li className="list-group-item">
            <Link to="/DangKyHP" className="btn btn-info btn-sm d-block mx-auto w-50 mb-2 fw-bold text-dark">Đăng Ký Học Phần</Link>
          </li>
          <li className="list-group-item">
            <Link to="/KetQuaHP" className="btn btn-info btn-sm d-block mx-auto w-50 mb-2 fw-bold text-dark">Xem Kết Quả Học Phần</Link>
          </li>
          <li className="list-group-item">
            <Link to="/LichThi" className="btn btn-info btn-sm d-block mx-auto w-50 mb-2 fw-bold text-dark">Xem Lịch Thi</Link>
          </li>
          <li className="list-group-item">
            <Link to="/NapTien" className="btn btn-info btn-sm d-block mx-auto w-50 mb-2 fw-bold text-dark">Nạp Tiền</Link>
          </li>
          <li className="list-group-item">
            <Link to="/QLTaiChinh" className="btn btn-info btn-sm d-block mx-auto w-50 mb-2 fw-bold text-dark">Thông Tin Tài Chính</Link>
          </li>
        </ul>
        <div className="text-center">
          <button 
            className="btn btn-danger btn-sm mt-3 w-50 text-white" 
            onClick={handleLogout}
          >
            Đăng Xuất
          </button>
        </div>
      </div>
    </div>
  );
}

export default TrangChu;
