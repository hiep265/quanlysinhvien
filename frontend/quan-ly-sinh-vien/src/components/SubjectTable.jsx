import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function SubjectTable() {
  const [subjects, setSubjects] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchSubjects = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/monhoc/all');
        if (!response.ok) {
          throw new Error('Failed to fetch subjects');
        }
        const data = await response.json();
        setSubjects(data);
      } catch (err) {
        setError('An error occurred while fetching the subjects. Please try again later.');
      } finally {
        setIsLoading(false);
      }
    };

    fetchSubjects();
  }, []);

  if (isLoading) {
    return <div className="text-center p-4">Loading...</div>;
  }

  if (error) {
    return <div className="text-center text-danger p-4">{error}</div>;
  }

  return (
    <div className="container mt-4">
      <h2 className="mb-4">Danh sách Môn học</h2>
      <div className="table-responsive">
        <table className="table table-striped table-hover">
          <thead className="bg-dark text-white">
            <tr>
              <th>Mã Môn Học</th>
              <th>Tên Môn Học</th>
              <th className="text-end">Tín Lý Thuyết</th>
              <th className="text-end">Tín Thực Hành</th>
              <th className="text-end">Tín Khác</th>
              <th className="text-end">Tiết Lý Thuyết</th>
              <th className="text-end">Tiết Thực Hành</th>
              <th className="text-end">Tiết Khác</th>
            </tr>
          </thead>
          <tbody>
            {subjects.map((subject) => (
              <tr key={subject.monHocId}>
                <td>{subject.monHocId}</td>
                <td>{subject.tenMonHoc}</td>
                <td className="text-end">{subject.tinLyThuyet}</td>
                <td className="text-end">{subject.tinThucHanh}</td>
                <td className="text-end">{subject.tinKhac}</td>
                <td className="text-end">{subject.tietLyThuyet}</td>
                <td className="text-end">{subject.tietThucHanh}</td>
                <td className="text-end">{subject.tietKhac}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default SubjectTable;

