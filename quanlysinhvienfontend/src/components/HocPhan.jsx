import { useState, useEffect } from 'react';


export default function HocPhan() {
  const [subjects, setSubjects] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchSubjects = async () => {
      try {
        const response = await fetch('http://localhost:8082/api/hoc_phan/all');
        if (!response.ok) {
          throw new Error('Failed to fetch subjects');
        }
        const data = await response.json();
        setSubjects(data.data);
      } catch (err) {
        setError(err+': An error occurred while fetching the subjects. Please try again later.');
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
      <h2 className="mb-4">Danh sách Học phần</h2>
      <div className="table-responsive">
        <table className="table table-striped table-hover">
          <thead className="bg-dark text-white">
            <tr>
              <th>Mã Học Phần</th>
              <th>Tên Môn Học</th>
              <th>Số Tín Chỉ</th>
              <th>Học Kỳ</th>
              <th>Năm Học</th>
            </tr>
          </thead>
          <tbody>
            {subjects.map((subject) => (
              <tr key={subject.hocPhanID}>
                <td>{subject.hocPhanID}</td>
                <td>{subject.monHocDto.tenMonHoc}</td>
                <td>{subject.soTinChi}</td>
                <td>{subject.hocKy}</td>
                <td>{subject.namHoc}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}



