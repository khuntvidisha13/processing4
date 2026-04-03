package processing.visual.src.test.shapes;

import org.junit.jupiter.api.*;
import processing.core.*;
import processing.visual.src.test.base.VisualTest;
import processing.visual.src.core.ProcessingSketch;
import processing.visual.src.core.TestConfig;

@Tag("shapes")
@Tag("primitives")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrimitiveShapeTest extends VisualTest {

    private ProcessingSketch createTest(ShapeCallback callback) {
        return new ProcessingSketch() {
            @Override
            public void setup(PApplet p) {
                p.background(200);
                p.fill(255);
                p.stroke(0);
            }

            @Override
            public void draw(PApplet p) {
                callback.draw(p);
            }
        };
    }

    @FunctionalInterface
    interface ShapeCallback {
        void draw(PApplet p);
    }

    // ===== BASIC SHAPE TESTS =====

    @Test
    @Order(1)
    @DisplayName("Drawing a rectangle")
    public void testRect() {
        assertVisualMatch("shapes/rect", createTest(p -> {
            p.rect(10, 10, 30, 30);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(2)
    @DisplayName("Drawing an ellipse")
    public void testEllipse() {
        assertVisualMatch("shapes/ellipse", createTest(p -> {
            p.ellipse(25, 25, 30, 20);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(3)
    @DisplayName("Drawing a triangle")
    public void testTriangle() {
        assertVisualMatch("shapes/triangle", createTest(p -> {
            p.triangle(25, 10, 10, 40, 40, 40);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(4)
    @DisplayName("Drawing an arc")
    public void testArc() {
        assertVisualMatch("shapes/arc", createTest(p -> {
            p.arc(25, 25, 30, 30, 0, PApplet.PI);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(5)
    @DisplayName("Drawing a line")
    public void testLine() {
        assertVisualMatch("shapes/line", createTest(p -> {
            p.line(10, 10, 40, 40);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(6)
    @DisplayName("Drawing a point")
    public void testPoint() {
        assertVisualMatch("shapes/point", createTest(p -> {
            p.strokeWeight(5);
            p.point(25, 25);
        }), new TestConfig(50, 50));
    }

    // ===== EDGE CASE TESTS =====

    @Test
    @Order(7)
    @DisplayName("Rectangle at canvas boundary")
    public void testRectBoundary() {
        assertVisualMatch("shapes/rect-boundary", createTest(p -> {
            p.rect(0, 0, 50, 50);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(8)
    @DisplayName("Zero size rectangle")
    public void testRectZeroSize() {
        assertVisualMatch("shapes/rect-zero", createTest(p -> {
            p.rect(10, 10, 0, 0);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(9)
    @DisplayName("Perfect circle")
    public void testCircle() {
        assertVisualMatch("shapes/circle", createTest(p -> {
            p.ellipse(25, 25, 30, 30);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(10)
    @DisplayName("Very small ellipse")
    public void testEllipseSmall() {
        assertVisualMatch("shapes/ellipse-small", createTest(p -> {
            p.ellipse(25, 25, 2, 2);
        }), new TestConfig(50, 50));
    }

    // ===== STROKE ONLY TESTS =====

    @Test
    @Order(11)
    @DisplayName("Stroke only rectangle")
    public void testRectStrokeOnly() {
        assertVisualMatch("shapes/rect-stroke", createTest(p -> {
            p.noFill();
            p.rect(10, 10, 30, 30);
        }), new TestConfig(50, 50));
    }

    @Test
    @Order(12)
    @DisplayName("Stroke only ellipse")
    public void testEllipseStrokeOnly() {
        assertVisualMatch("shapes/ellipse-stroke", createTest(p -> {
            p.noFill();
            p.ellipse(25, 25, 30, 20);
        }), new TestConfig(50, 50));
    }
}